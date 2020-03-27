package com.example.cart.service;

import com.example.cart.model.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 吴玥
 * @Title IProductService
 * @Description
 * @date 2020/1/6
 */
public interface IProductService {
    /**
     * 查询所有商品项,分页
     * @return
     */
    public PageInfo<Product> findAllProduct(int pageNum);
    /**
     * 根据商品编号查询单个商品
     * @param pid
     * @return
     */
    public Product findProductByPid(int pid);

}
