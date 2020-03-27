package com.example.cart.api.handler;

import com.example.cart.base.ResponseBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吴玥
 * @Title ICartHandler
 * @Description
 * @date 2020/1/6
 */
public interface ICartHandler {
    @RequestMapping(value = "/CartgetProduct",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBase CartgetProduct(@RequestParam("name") String name);

    @GetMapping("/AddProductToCart/{pid}")
    public ResponseBase AddProductToCart(@RequestHeader("Authorization") String token, @PathVariable("pid")int pid);

    @GetMapping("/RemoveProductFromCart/{pid}")
    public ResponseBase RemoveProductFromCart(@RequestHeader("Authorization") String token,@PathVariable("pid")int pid);

    @GetMapping("/findAllCartItem")
    public ResponseBase findAllCartItems(@RequestHeader("Authorization") String token);
}
