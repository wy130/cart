package com.example.cart.api.handler;

import com.example.cart.base.ResponseBase;
import com.example.cart.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吴玥
 * @Title IProductHandler
 * @Description
 * @date 2020/1/6
 */
public interface IProductHandler {
    @RequestMapping(value = "/getProduct")
    public ResponseBase getProduct(@RequestParam("name") String name);

    @GetMapping(value = "/findAllProduct",name = "findAllProduct")
    public ResponseBase findAllProduct(@RequestParam(defaultValue="1",name = "pageNum") int pageNum);

    @GetMapping(value = "/findProductByPid/{pid}",name = "findProductByPid")
    public ResponseBase<Product> findProductByPid(@PathVariable("pid") int pid) ;

}
