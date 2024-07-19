package com.example.nogiback.entity;

public class UpdateCoinsRequest {
    private String userName;
    private int needCoins;
    private String token;

    private String memberId;

    private int memberMark;

    public int getMemberMark() {
        return memberMark;
    }

    public void setMemberMark(int memberMark) {
        this.memberMark = memberMark;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNeedCoins() {
        return needCoins;
    }

    public void setNeedCoins(int needCoins) {
        this.needCoins = needCoins;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UpdateCoinsRequest{" +
                "userName='" + userName + '\'' +
                ", needCoins=" + needCoins +
                ", token='" + token + '\'' +
                '}';
    }
}
