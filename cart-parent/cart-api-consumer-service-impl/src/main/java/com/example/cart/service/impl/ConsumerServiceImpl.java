package com.example.cart.service.impl;


import com.example.cart.mapper.ConsumerMapper;
import com.example.cart.model.Consumer;
import com.example.cart.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 吴玥
 * @Title ConsumerServiceImpl
 * @Description
 * @date 2020/1/2
 */
@Service
@CacheConfig(cacheNames = {"consumer"})
public class ConsumerServiceImpl implements IConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;
    @Override
    @Cacheable(value = "consumer" ,key = "targetClass + methodName +#p0")
    public Consumer findConsumerByCnickname(String cnickname) {
        return consumerMapper.selectByByCnickname(cnickname);
    }

    @Override
    @Cacheable(value = "consumer" ,key = "targetClass + methodName +#p0")
    public Consumer checkLoginByNicknameAndPassword(String cnickname, String cpassword) {
        return consumerMapper.checkLoginByNicknameAndPassword(cnickname, cpassword);
    }
}
