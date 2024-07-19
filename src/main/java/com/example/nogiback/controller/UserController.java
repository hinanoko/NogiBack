package com.example.nogiback.controller;

import com.example.nogiback.entity.LoginResponse;
import com.example.nogiback.entity.UpdateCoinsRequest;
import com.example.nogiback.entity.User;
import com.example.nogiback.service.MarkService;
import com.example.nogiback.service.UserService;
import com.example.nogiback.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MarkService markService;

    @PostMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            System.out.println(user);
            User addUser = userService.addUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User added successfully");
            response.put("user", addUser);
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User addition failed");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            LoginResponse whetherSuccess = userService.checkPassword(user);
            System.out.println(whetherSuccess);
            if ("success to verify".equals(whetherSuccess.getMessage())) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("token", user.getUser_token());
                response.put("coins", whetherSuccess.getCoins());
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.badRequest().body("Login failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/updateCoins")
    public ResponseEntity<String> updateCoins(@RequestBody UpdateCoinsRequest request) {
        System.out.println(request.getToken());
        int memberId = Integer.parseInt(request.getMemberId());
        System.out.println(memberId);
        System.out.println(request.getMemberMark());
        try {
            boolean isSuccess = userService.updateUserCoins(request.getUserName(), request.getNeedCoins(), request.getToken());
            markService.updateMemberMarks(memberId, request.getMemberMark());
            if (isSuccess) {
                return ResponseEntity.ok("Coins updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update coins.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }
}
