package com.example.nogiback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper

public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT user_password from user where user_name = #{userName}")
    String getPasswordByName(String userName);

    @Select("SELECT * FROM user WHERE user_name = #{userName}")
    User getUserByName(@Param("userName") String userName);

    @Select("SELECT user_coins from user where user_name = #{userName}")
    Integer getCoinsByName(String useName);

    @Update("UPDATE user SET user_token = #{token} WHERE user_name = #{userName}")
    int updateTokenByName(@Param("userName") String userName, @Param("token") String token);

    @Update("UPDATE user SET user_coins = user_coins - #{coins} WHERE user_name = #{userName}")
    int updateCoinsByName(@Param("userName") String userName, @Param("coins") int coins);

    @Transactional
    @Update("UPDATE user SET user_coins = COALESCE(user_coins, 0) + 1")
    Integer addCoinsToAllUsers();

    @Select("SELECT user_id FROM user WHERE user_name = #{userName}")
    Integer getUserIdByName(@Param("userName") String userName);

    @Select("SELECT user_name FROM user WHERE user_id = #{userId}")
    String getUserNameById(@Param("userId") Integer userId);

}
