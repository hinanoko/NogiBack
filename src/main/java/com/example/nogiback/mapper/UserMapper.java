package com.example.nogiback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface UserMapper extends BaseMapper<User> {

}
