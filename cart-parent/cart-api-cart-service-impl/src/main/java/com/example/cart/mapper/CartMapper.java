package com.example.cart.mapper;

import com.example.cart.model.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    int deleteByCidAndPid(Cart record);

    Cart selectByCidAndPid(@Param("cid") int cid, @Param("pid") int pid);

    int updateByCidAndPid(Cart record);


}