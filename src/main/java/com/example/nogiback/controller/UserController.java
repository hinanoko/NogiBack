package com.example.nogiback.controller;


import com.example.nogiback.entity.User;
import com.example.nogiback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
       try {
            System.out.println(user);
            User addUser = userService.addUser(user);
           Map<String, Object> response = new HashMap<>();
           response.put("message", "User added successfully");
           response.put("user", addUser);
           return ResponseEntity.ok().body(response);
        }catch(Exception e){
             Map<String, Object> response = new HashMap<>();
             response.put("message", "User added fail");
             response.put("user", e.getMessage());
             return ResponseEntity.ok().body(response);
        }
    }
}
