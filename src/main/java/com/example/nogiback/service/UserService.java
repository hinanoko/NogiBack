package com.example.nogiback.service;


import com.example.nogiback.entity.Gamble;
import com.example.nogiback.entity.LoginResponse;
import com.example.nogiback.entity.User;
import com.example.nogiback.mapper.UserMapper;
import com.example.nogiback.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public User addUser(User user) {
        String realName = user.getUser_name();
        if (userMapper.getUserByName(realName) != null) {
            throw new RuntimeException("Username already exists");
        }
        System.out.println("Adding user: " + user);
        int userAddResult = userMapper.insert(user);
        if(userAddResult > 0){
            return user;
        } else {
            throw new RuntimeException("User addition failed");
        }
    }

    public LoginResponse checkPassword(User user){
        String realName = user.getUser_name();
        String realPassword = userMapper.getPasswordByName(realName);
        if(realPassword.equals(user.getUser_password())){
            String token = JwtUtil.createToken(user.getUser_name());
            user.setUser_token(token);
            int updateResult = userMapper.updateTokenByName(realName, token);
            Integer coins = userMapper.getCoinsByName(user.getUser_name());
            if (updateResult > 0) {
                return new LoginResponse("success to verify", token, coins);
            } else {
                throw new RuntimeException("Token update failed");
            }
        } else {
            return new LoginResponse("fail to verify", null, 0);
        }
    }

    public boolean updateUserCoins(String userName, int coins, String token) {
        if (JwtUtil.checkToken(token)) {
            int result = userMapper.updateCoinsByName(userName, coins);
            return result > 0;
        } else {
            throw new RuntimeException("Invalid token");
        }
    }


    @Transactional
    @Scheduled(fixedRate = 3000000)
    public void addCoinsToUser(){
        System.out.println("Adding coins to users...");
        userMapper.addCoinsToAllUsers();
        System.out.println("Coins added successfully.");
    }


}
