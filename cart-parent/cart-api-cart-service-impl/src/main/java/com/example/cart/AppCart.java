package com.example.cart;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 吴玥
 * @Title AppCart
 * @Description
 * @date 2019/12/11
 */
@MapperScan("com.example.cart.mapper")
@ComponentScan("com.example.cart.api")
@ComponentScan("com.example.cart.service")
@ComponentScan("com.example.cart.config")
@ComponentScan("com.example.cart.util")
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
@EnableCaching
public class AppCart {
    public static void main(String[] args) {
        SpringApplication.run(AppCart.class,args);
        }
}
