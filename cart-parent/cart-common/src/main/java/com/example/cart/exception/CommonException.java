package com.example.cart.exception;

/**
 * @author 吴玥
 * @Title CommonException
 * @Description
 * @date 2020/1/7
 */

import com.example.cart.entity.ResultCode;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CommonException extends Exception  {

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
