package com.example.nogiback.service;


import com.example.nogiback.entity.Gamble;
import com.example.nogiback.mapper.GambleMapper;
import com.example.nogiback.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GambleService {
    private GambleMapper gambleMapper;

    @Autowired
    public GambleService(GambleMapper gambleMapper){this.gambleMapper = gambleMapper;}

    public String addGamble(Gamble gamble, String token) {
        if (JwtUtil.checkToken(token)) {
            System.out.println("token right");
            int num = gambleMapper.countByMemberName(gamble.getUserName());
            if(num == 0) {
                try {
                    gambleMapper.insertGamble(gamble);
                    System.out.println("insert success");
                    return "Gamble inserted successfully";
                } catch (DataAccessException e) {
                    System.out.println(e);
                    System.out.println(gamble);
                    System.out.println("insert fail");
                    return "Failed to insert gamble: ";
                }
            }else {
                gambleMapper.updateByUserName(gamble.getUserName(), gamble);
                return "Gamble changed successfully";
            }
        }else{
            throw new RuntimeException("Invalid token");
        }
    }

    public Map<String, Object> selectGambleByName(String name) {
        Map<String, Object> response = new HashMap<>();
        try {
            Gamble gotGamble = gambleMapper.selectByUserName(name);
            if (gotGamble != null) {
                response.put("serviceMessage", "success");
                response.put("data", gotGamble);
            } else {
                response.put("serviceMessage", "fail to find");
            }
        } catch (Exception e) {
            response.put("serviceMessage", "error");
            response.put("error", e.getMessage());
        }
        return response;
    }
}
