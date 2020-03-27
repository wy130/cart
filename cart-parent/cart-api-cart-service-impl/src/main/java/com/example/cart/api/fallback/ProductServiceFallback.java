package com.example.cart.api.fallback;

import com.example.cart.api.feign.ProductServiceFeign;
import com.example.cart.base.BaseApiService;
import com.example.cart.base.ResponseBase;
import com.example.cart.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 吴玥
 * @Title ProductServiceFallback
 * @Description
 * @date 2020/1/6
 */
@Component
public class ProductServiceFallback extends BaseApiService implements ProductServiceFeign{

    @Override
    public ResponseBase getProduct(String name) {
        return null;
    }

    @Override
    public ResponseBase findAllProduct(int pageNum) {
        return setResultError("服务器忙，请稍后重试（以类的形式服务降级）");
    }

    @Override
    public ResponseBase findProductByPid(int pid) {
        return setResultError("服务器忙，请稍后重试（以类的形式服务降级）");
    }
}
