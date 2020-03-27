package com.example.cart.api.feign;

import com.example.cart.api.fallback.ProductServiceFallback;
import com.example.cart.api.handler.IProductHandler;
import com.example.cart.base.ResponseBase;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * @author 吴玥
 * @Title ProductServiceFeign
 * @Description
 * @date 2020/1/6
 */
@FeignClient(value = "app-cart-product",fallback = ProductServiceFallback.class)
public interface ProductServiceFeign extends IProductHandler {


}
