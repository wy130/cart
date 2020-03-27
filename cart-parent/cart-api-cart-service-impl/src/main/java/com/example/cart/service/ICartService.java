package com.example.cart.service;

import com.example.cart.model.Cart;
import com.example.cart.model.Consumer;
import com.example.cart.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吴玥
 * @Title ICartService
 * @Description
 * @date 2020/1/6
 */
public interface ICartService {
    /**
     * 将单个商品加入购物车
     * @param product
     * @return
     */
    public Cart AddProductToCart(Consumer consumer,Product product);
    /**
     * 从购物车中删除商品

     * @return
     */
    public int RemoveProductFromCart(int cid,int pid);

    /**
     * 查询所有购物车项
     * @return
     */
    public List<Cart> findAllCartItems(int cid);

}
