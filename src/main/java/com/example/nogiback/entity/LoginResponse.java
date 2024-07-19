package com.example.nogiback.entity;

public class LoginResponse {
    private String message;
    private String token;

    private Integer coins;

    public LoginResponse(String message, String token, Integer coins) {
        this.message = message;
        this.token = token;
        this.coins = coins;
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
}