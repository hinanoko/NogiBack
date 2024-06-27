package com.example.nogiback.service;


import com.example.nogiback.entity.User;
import com.example.nogiback.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public User addUser(User user) {
        System.out.println("Adding user: " + user);
        int userAddResult = userMapper.insert(user);
        if(userAddResult > 0){
            return user;
        }else {
            throw new RuntimeException();
        }
    }


}
