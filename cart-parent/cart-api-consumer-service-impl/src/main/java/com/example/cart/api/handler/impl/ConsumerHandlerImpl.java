package com.example.cart.api.handler.impl;


import com.example.cart.annotation.SysLog;
import com.example.cart.entity.Result;
import com.example.cart.entity.ResultCode;
import com.example.cart.entity.UserEntity;
import com.example.cart.api.handler.IConsumerHandler;
import com.example.cart.base.BaseApiService;
import com.example.cart.base.ResponseBase;
import com.example.cart.exception.CommonException;
import com.example.cart.model.Consumer;
import com.example.cart.service.IConsumerService;
import com.example.cart.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 吴玥
 * @Title ConsumerHandlerImpl
 * @Description
 * @date 2019/12/11
 */
@Slf4j
//@RequestMapping("/apiConsumer")
@RestController
public class ConsumerHandlerImpl extends BaseApiService implements IConsumerHandler {
    @Autowired(required = true)
    private IConsumerService consumerService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private JwtUtils jwtUtils;

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/getMember", name = "getMember")
    public UserEntity getMember(@RequestParam("name") String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name + "端口号:" + serverPort);
        userEntity.setAge(20);
        return userEntity;
    }


    @PostMapping(value = "/login", name = "login")
    @Override
    public Result<Consumer> login(@RequestParam("cnickname") String cnickname, @RequestParam("cpassword") String cpassword) {
        Consumer consumer = consumerService.findConsumerByCnickname(cnickname);
        String password = getMD5(cpassword);//解析完的MD5加密密码
        if (consumer == null || !consumer.getCpassword().equals(password)) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        } else {
            return new Result(ResultCode.SUCCESS, consumer);
        }

    }

    /**
     * 用户登录
     * 1.通过service根据mobile查询用户
     * 2.比较password
     * 3.生成jwt信息
     */
    @SysLog("用户登录")
    @GetMapping(value = "/loginByJwt", name = "loginByJwt")
    @Override
    public Result<Consumer> loginByJwt(@RequestParam("cnickname") String cnickname, @RequestParam("cpassword") String cpassword) {
        Consumer consumer = consumerService.findConsumerByCnickname(cnickname);
        //登录失败
        if (consumer == null || !consumer.getCpassword().equals(cpassword)) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        } else {
            //登录成功
            //api权限字符串
            StringBuilder sb = new StringBuilder();
            sb.append(consumer.getPermissions());
            Map<String, Object> map = new HashMap<>();
            map.put("apis", sb.toString());//可访问的api权限字符串
            map.put("address", consumer.getAddress());
            map.put("phone", consumer.getCphone());
            String token = jwtUtils.createJwt(consumer.getCnickname(), consumer.getCname(), map);
            Result<Consumer> result = new Result(ResultCode.SUCCESS, consumer);
            result.setData(token);
            log.info("登陆成功");
            return result;
        }
    }


    /**
     * 用户登录成功之后，获取用户信息
     * 1.获取用户id
     * 2.根据用户id查询用户
     * 3.构建返回值对象
     * 4.响应
     */
    @SysLog("取出用户信息")
    @GetMapping(value = "/profile", name = "profile")
    @Override
    public Result<Consumer> profile(@RequestHeader("Authorization") String token) {
        System.out.println("此时从用户服务中获取当前登录用户信息，token为" + token);
        String cnickname = claims.getId();

        //获取用户信息
        Consumer consumer = consumerService.findConsumerByCnickname(cnickname);
        //根据不同的用户级别获取用户权限


        return new Result(ResultCode.SUCCESS, consumer);
    }


}
