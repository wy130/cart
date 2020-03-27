package com.example.cartconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableDiscoveryClient
//@ComponentScan(value = "com.njxz.springcloudconfigclient.controller")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RefreshScope
public class CartConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartConfigClientApplication.class, args);
    }

}
