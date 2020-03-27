package com.example.cart.api.fallback;

import com.example.cart.api.feign.ConsumerServiceFeign;
import com.example.cart.base.BaseApiService;
import com.example.cart.base.ResponseBase;
import com.example.cart.entity.Result;
import com.example.cart.entity.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 吴玥
 * @Title ConsumerServiceFallback
 * @Description
 * @date 2020/1/9
 */
@Component
public class ConsumerServiceFallback extends BaseApiService implements ConsumerServiceFeign {
    @Override
    public Result login(String cnickname, String cpassword) {
        return new Result(ResultCode.SERVER_ERROR);
    }

    @Override
    public Result loginByJwt(String cnickname, String cpassword) {
        return new Result(ResultCode.SERVER_ERROR);
    }

    @Override
    public Result profile(@RequestHeader("Authorization") String token) {
        return new Result(ResultCode.SERVER_ERROR);
    }
}
