package com.example.cart.annotation;

import java.lang.annotation.*;

/**
 * @author 吴玥
 * @Title SysLog
 * @Description
 * @date 2020/1/16
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";

}
