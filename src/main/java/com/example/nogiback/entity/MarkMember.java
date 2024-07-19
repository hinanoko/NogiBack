package com.example.nogiback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("markmember")
@Data
public class MarkMember {
    @TableId(type = IdType.NONE)
    private Integer memberId;
    private String memberName;

    private String memberState;

    private int memberPosiMark;

    private int memberNogiMark;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberState() {
        return memberState;
    }

    public void setMemberState(String memberState) {
        this.memberState = memberState;
    }

    public int getMemberPosiMark() {
        return memberPosiMark;
    }

    public void setMemberPosiMark(int memberPosiMark) {
        this.memberPosiMark = memberPosiMark;
    }

    public int getMemberNogiMark() {
        return memberNogiMark;
    }

    public void setMemberNogiMark(int memberNogiMark) {
        this.memberNogiMark = memberNogiMark;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberName='" + memberName + '\'' +
                ", memberId=" + memberId +
                ", memberState='" + memberState + '\'' +
                ", memberPosiMark=" + memberPosiMark +
                ", memberNogiMark=" + memberNogiMark +
                '}';
    }
}
