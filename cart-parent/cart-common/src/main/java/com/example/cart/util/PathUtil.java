package com.example.cart.util;

import org.springframework.util.AntPathMatcher;

/**
 * @author 吴玥
 * @Title PathUtil
 * @Description 封装路径匹配方法，用于判断请求的接口是否是需要拦截的接口
 * @date 2020/1/9
 */
public class PathUtil {
    private static AntPathMatcher matcher = new AntPathMatcher();
    public static boolean isPathMatch(String pattern, String path) {
        return matcher.match(pattern, path);
    }
}
