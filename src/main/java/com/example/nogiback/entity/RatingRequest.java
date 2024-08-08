package com.example.nogiback.entity;

public class RatingRequest {
    private int customerId;
    private int memberId;
    private int memberMark;

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getMemberMark() {
        return memberMark;
    }

    public void setMemberMark(int memberMark) {
        this.memberMark = memberMark;
    }
}
