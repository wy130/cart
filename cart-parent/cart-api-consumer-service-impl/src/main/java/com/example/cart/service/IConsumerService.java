package com.example.cart.service;

import com.example.cart.model.Consumer;

/**
 * @author 吴玥
 * @Title IConsumerService
 * @Description
 * @date 2020/1/2
 */
public interface IConsumerService {
    /**
     * 根据昵称来查找用户
     * @param cnickname
     */
    public Consumer findConsumerByCnickname(String cnickname);

    /**
     * 根据昵称和密码检验登录
     * @param cnickname
     * @param cpassword
     * @return
     */
    public Consumer checkLoginByNicknameAndPassword(String cnickname,String cpassword);

}
