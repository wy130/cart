package com.example.cart.service.impl;

import com.example.cart.mapper.ProductMapper;
import com.example.cart.model.Product;
import com.example.cart.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 吴玥
 * @Title ProductServiceImpl
 * @Description
 * @date 2020/1/6
 */
@Service
@CacheConfig(cacheNames = {"product"})
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    @Cacheable(value = "product" ,key = "targetClass + methodName +#p0")
    public PageInfo<Product> findAllProduct(int pageNum) {
        PageHelper.startPage(pageNum, 2);
        List<Product> products = productMapper.selectAllProduct();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "product" ,key = "targetClass + methodName +#p0")
    public Product findProductByPid(int pid) {
        return productMapper.selectByPrimaryKey(pid);
    }
}
