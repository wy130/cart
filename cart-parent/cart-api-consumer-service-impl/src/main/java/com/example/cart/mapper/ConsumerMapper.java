package com.example.cart.mapper;




import com.example.cart.model.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.ComponentScan;

@Mapper
public interface ConsumerMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Consumer record);

    int insertSelective(Consumer record);

    Consumer selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);

    Consumer selectByByCnickname(String cnickname);

    Consumer checkLoginByNicknameAndPassword(@Param("cnickname")String cnickname, @Param("cpassword") String cpassword);

}