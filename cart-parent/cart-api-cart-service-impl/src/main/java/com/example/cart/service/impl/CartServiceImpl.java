package com.example.cart.service.impl;

import com.example.cart.mapper.CartMapper;
import com.example.cart.model.Cart;
import com.example.cart.model.Consumer;
import com.example.cart.model.Product;
import com.example.cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吴玥
 * @Title CartServiceImpl
 * @Description
 * @date 2020/1/6
 */
@Service
@CacheConfig(cacheNames = {"cart"})
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
   // @Cacheable(value = "cart", key = "#p0.cid+'---'+#product.pid")
    public Cart AddProductToCart(Consumer consumer,Product product) {
        Cart cart = cartMapper.selectByCidAndPid(consumer.getCid(),product.getPid());
        if (cart!=null){
            Integer choice = cart.getChoice()+1;

            cart.setChoice(choice);
            return cartMapper.updateByCidAndPid(cart)>0?cart:null;
        }
        Cart cart1 = new Cart();
        cart1.setCid(consumer.getCid());
        cart1.setPid(product.getPid());
        cart1.setPname(product.getPname());
        cart1.setProduct(product);
        cart1.setChoice(1);
        return cartMapper.insertSelective(cart1)>0?cart1:null;
    }

    @Override
    //@CacheEvict(value = "cart", key = "#p0+'---'+#pid")//清空缓存
    public int RemoveProductFromCart(int cid,int pid) {
        Cart cartItem = new Cart();
        cartItem.setCid(cid);
        cartItem.setPid(pid);
        return cartMapper.deleteByCidAndPid(cartItem);
    }
    @Override
    //@Cacheable(value = "cart", key = "targetClass + methodName +#p0")
    public List<Cart> findAllCartItems(int cid) {

        return cartMapper.selectByPrimaryKey(cid);
    }

}
