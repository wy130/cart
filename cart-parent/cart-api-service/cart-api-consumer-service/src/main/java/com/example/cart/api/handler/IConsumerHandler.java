package com.example.cart.api.handler;

import com.example.cart.entity.Result;
import com.example.cart.entity.UserEntity;

import com.example.cart.base.ResponseBase;
import com.example.cart.model.Consumer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 吴玥
 * @Title IConsumerHandler
 * @Description
 * @date 2019/12/11
 */

public interface IConsumerHandler {
// 实体类是存放接口项目还是 存放在实现项目 实体类存放在接口项目里面
    // 实体类和定义接口信息存放在接口项目
    // 代码实现存放在接口实现类里面

//    @RequestMapping("/getMember")
//    public UserEntity getMember(@RequestParam("name") String name);
//
//    @RequestMapping("/getUserInfo")
//    public ResponseBase getUserInfo();

    @RequestMapping("/login")
    public Result<Consumer> login(@RequestParam("cnickname") String cnickname, @RequestParam("cpassword") String cpassword);

    @GetMapping(value="/loginByJwt",name = "loginByJwt")
    public Result<Consumer> loginByJwt(@RequestParam("cnickname") String cnickname, @RequestParam("cpassword")String cpassword);
    @GetMapping(value="/profile",name = "profile")
    public Result<Consumer> profile(@RequestHeader("Authorization") String token);
}
