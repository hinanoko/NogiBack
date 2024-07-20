package com.example.nogiback.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("gamble")
@Data
public class Gamble {
@TableId(type = IdType.NONE)
    private String userName;

    private String determine;

    private Integer thirdRowNum;

    private Integer secondRowNum;

    private Integer firstRowNum;

    private String thirdRowMember;

    private String secondRowMember;

    private String firstRowMember;

    private String senBatuC;

    private String underC;

    private String title;

    private String date;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetermine() {
        return determine;
    }

    public void setDetermine(String determine) {
        this.determine = determine;
    }

    public Integer getThirdRowNum() {
        return thirdRowNum;
    }

    public void setThirdRowNum(Integer thirdRowNum) {
        this.thirdRowNum = thirdRowNum;
    }

    public Integer getSecondRowNum() {
        return secondRowNum;
    }

    public void setSecondRowNum(Integer secondRowNum) {
        this.secondRowNum = secondRowNum;
    }

    public Integer getFirstRowNum() {
        return firstRowNum;
    }

    public void setFirstRowNum(Integer firstRowNum) {
        this.firstRowNum = firstRowNum;
    }

    public String getThirdRowMember() {
        return thirdRowMember;
    }

    public void setThirdRowMember(String thirdRowMember) {
        this.thirdRowMember = thirdRowMember;
    }

    public String getSecondRowMember() {
        return secondRowMember;
    }

    public void setSecondRowMember(String secondRowMember) {
        this.secondRowMember = secondRowMember;
    }

    public String getFirstRowMember() {
        return firstRowMember;
    }

    public void setFirstRowMember(String firstRowMember) {
        this.firstRowMember = firstRowMember;
    }

    public String getSenBatuC() {
        return senBatuC;
    }

    public void setSenBatuC(String senBatuC) {
        this.senBatuC = senBatuC;
    }

    public String getUnderC() {
        return underC;
    }

    public void setUnderC(String underC) {
        this.underC = underC;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Gamble{" +
                "userName='" + userName + '\'' +
                ", determine='" + determine + '\'' +
                ", thirdRowNum=" + thirdRowNum +
                ", secondRowNum=" + secondRowNum +
                ", firstRowNum=" + firstRowNum +
                ", thirdRowMember='" + thirdRowMember + '\'' +
                ", secondRowMember='" + secondRowMember + '\'' +
                ", firstRowMember='" + firstRowMember + '\'' +
                ", senBatuC='" + senBatuC + '\'' +
                ", underC='" + underC + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
