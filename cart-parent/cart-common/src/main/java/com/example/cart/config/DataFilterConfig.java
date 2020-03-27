package com.example.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author 吴玥
 * @Title DataFilterConfig
 * @Description
 * @date 2020/1/9
 */
@ConfigurationProperties(prefix = "data-filter", ignoreUnknownFields = false)
@Data
@Component
public class DataFilterConfig {
    private List<String> authPath;
    private List<String> userLoginPath;
}
