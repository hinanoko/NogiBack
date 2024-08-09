package com.example.nogiback.entity;

public class LoginResponse {
    private String message;
    private String token;

    private Integer coins;

    private Integer userId;

    public LoginResponse(String message, String token, Integer coins, Integer userId) {
        this.message = message;
        this.token = token;
        this.coins = coins;
        this.userId = userId;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCoins(){
        return coins;
    }

    public void setCoins(Integer coins){
        this.coins = coins;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}