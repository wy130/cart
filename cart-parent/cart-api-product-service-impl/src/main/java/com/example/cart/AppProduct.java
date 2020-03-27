package com.example.cart;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 吴玥
 * @Title AppProduct
 * @Description
 * @date 2019/12/11
 */
@MapperScan("com.example.cart.mapper")
@ComponentScan("com.example.cart.api.handler.impl")
@ComponentScan("com.example.cart.service")
@ComponentScan("com.example.cart.config")
@ComponentScan("com.example.cart.util")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCaching
public class AppProduct {
    public static void main(String[] args) {
        SpringApplication.run(AppProduct.class,args);
        }
}
