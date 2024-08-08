package com.example.nogiback.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("rankrating")
@Data
public class CustomerRank {

    @TableId(type = IdType.AUTO)
    private int ratingId;

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Boolean getIsMark() {
        return isMark;
    }

    public void setIsMark(Boolean isMark) {
        this.isMark = isMark;
    }

    public Integer getMemberMark() {
        return memberMark;
    }

    public void setMemberMark(Integer memberMark) {
        this.memberMark = memberMark;
    }

    private Integer customer_id;

    private Integer member_id;

    private Boolean isMark;

    private Integer memberMark;
}
