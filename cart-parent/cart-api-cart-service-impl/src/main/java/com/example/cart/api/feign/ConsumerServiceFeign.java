package com.example.cart.api.feign;

import com.example.cart.api.fallback.ConsumerServiceFallback;
import com.example.cart.api.handler.IConsumerHandler;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 吴玥
 * @Title ConsumerServiceFeign
 * @Description
 * @date 2020/1/9
 */
@FeignClient(value = "app-cart-consumer",fallback = ConsumerServiceFallback.class)
public interface ConsumerServiceFeign extends IConsumerHandler {
}
