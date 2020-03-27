package com.example.cart.config;

import com.example.cart.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author 吴玥
 * @Title WebMvcConfigurer
 * @Description
 * @date 2019/12/24
 */
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
    @Bean
    public JwtInterceptor myInterceptor(){
        return new JwtInterceptor();
    }
    /**
     *配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).
                ///AddProductToCart/**","RemoveProductFromCart","findAllCartItem"
        addPathPatterns("/**").//2.指定拦截器的url地址
                excludePathPatterns("/loginByJwt","/login","/findAllProduct","/findProductByPid/**");//3.指定不拦截的url地址

    }

}
