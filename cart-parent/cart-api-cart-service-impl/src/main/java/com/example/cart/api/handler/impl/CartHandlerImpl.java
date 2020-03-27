package com.example.cart.api.handler.impl;

import com.example.cart.annotation.SysLog;
import com.example.cart.api.feign.ConsumerServiceFeign;
import com.example.cart.api.feign.ProductServiceFeign;
import com.example.cart.api.handler.ICartHandler;
import com.example.cart.base.BaseApiService;
import com.example.cart.base.ResponseBase;
import com.example.cart.entity.Result;
import com.example.cart.model.Cart;
import com.example.cart.model.Consumer;
import com.example.cart.model.Product;
import com.example.cart.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 吴玥
 * @Title CartHandlerImpl
 * @Description
 * @date 2020/1/6
 */
//@Slf4j
//@RequestMapping("/apiCart")
@RestController
public class CartHandlerImpl extends BaseApiService implements ICartHandler {
    @Autowired
    private ProductServiceFeign productServiceFeign;
    @Autowired
    private ICartService cartService;
    @Autowired
    private ConsumerServiceFeign consumerServiceFeign;


    /**
     * 测试负载均衡效果
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/CartgetProduct", name = "CartgetProduct")
    @Override
    public ResponseBase CartgetProduct(@RequestParam("name") String name) {

        return productServiceFeign.getProduct(name);
    }


    /**
     * 将单个商品加入购物车
     *
     * @param pid
     * @return
     */
    //@SysLog("将单个商品加入购物车")
    @GetMapping(value = "/AddProductToCart/{pid}", name = "AddProductToCart")
    @Override
    public ResponseBase<Cart> AddProductToCart(@RequestHeader("Authorization") String token, @PathVariable("pid") int pid) {
        //从product服务中根据pid查询到单个商品
        System.out.println(token);
        ResponseBase<Product> responseBase = productServiceFeign.findProductByPid(pid);
        Product product = null;
        if (responseBase != null) {
            product = responseBase.getT();
        }
        //从消费者服务中调用当前登录用户的信息
        Result<Consumer> result = consumerServiceFeign.profile(token);
        if (result == null) {
            //log.info("检测到当前没有登录用户");
            return setResultError("当前没有用户登录");
        }
        Consumer consumer = (Consumer) result.getT();
        //log.info("检测到当前登录用户为"+consumer.getCnickname());
        if (product != null) {
            //将查询到的product加入当前用户的购物车 生成对应的一条购物车商品项cart
            Cart cart = cartService.AddProductToCart(consumer, product);
            if (cart != null) {
                //log.info("加入购物车成功");
                return setResult(200, "加入购物车成功", cart);
            }
            // log.info("添加购物车失败"+product);
            return setResult(500, "添加购物车失败", product);
        }
        // log.info("查询该商品失败");
        return setResultError("查询该商品失败");
    }

    /**
     * 从购物车中删除商品
     *
     * @param pid
     * @return
     */
    @Override
    //@SysLog("从购物车中删除商品")
    @GetMapping(value = "/RemoveProductFromCart/{pid}", name = "RemoveProductFromCart")
    public ResponseBase<Cart> RemoveProductFromCart(@RequestHeader("Authorization") String token, @PathVariable("pid") int pid) {
        //从消费者服务中调用当前登录用户的信息
        Result<Consumer> result = consumerServiceFeign.profile(token);
        if (result == null) {
            // log.info("检测到当前没有登录用户");
            return setResultError("当前没有用户登录");
        }
        Consumer consumer = result.getT();
        //log.info("检测到当前登录用户为"+consumer.getCnickname());
        if (cartService.RemoveProductFromCart(consumer.getCid(), pid) > 0) {
            // log.info("从购物车删除商品编号为"+pid+"的商品成功");
            return setResultSuccess("删除成功");
        }
        return setResultError("删除失败");
    }

    /**
     * 查询所有购物车项
     *
     * @return
     */
    @Override
    @GetMapping(value = "/findAllCartItem", name = "findAllCartItem")
    //@SysLog("查询所有购物车项")
    public ResponseBase<Cart> findAllCartItems(@RequestHeader("Authorization") String token) {
        //从消费者服务中调用当前登录用户的信息
        Result<Consumer> result = consumerServiceFeign.profile(token);
        if (result == null) {
            // log.info("检测到当前没有登录用户");
            return setResultError("当前没有用户登录");
        }
        Consumer consumer = result.getT();
        // log.info("检测到当前登录用户为"+consumer.getCnickname());
        List<Cart> carts = cartService.findAllCartItems(consumer.getCid());
        if (carts != null) {
            // log.info("查询购物车成功");
            return setResult(200, "查询-" + consumer.getCnickname() + "-用户的购物车成功", carts);

        }
        // log.info("查询购物车失败");
        return setResultError("查看购物车失败");
    }
}
