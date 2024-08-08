package com.example.nogiback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@TableName("rankmember")
@Data
public class RankMember {

@TableId(type = IdType.NONE)

    private int rankMemberId;

    private Integer score_p5_count;

    private Integer score_p4_count;

    private Integer score_p3_count;

    private Integer score_p2_count;

    private Integer score_p1_count;

    private Integer score_n1_count;

    private Integer score_n2_count;

    private Integer score_n3_count;

    private Integer score_n4_count;

    private Integer score_n5_count;

    private Double totalRank;

    // Getters and Setters

    public int getRankMemberId() {
        return rankMemberId;
    }

    public void setRankMemberId(int rankMemberId) {
        this.rankMemberId = rankMemberId;
    }

    public Integer getScoreP5Count() {
        return score_p5_count;
    }

    public void setScoreP5Count(Integer score_p5_count) {
        this.score_p5_count = score_p5_count;
    }

    public Integer getScoreP4Count() {
        return score_p4_count;
    }

    public void setScoreP4Count(Integer score_p4_count) {
        this.score_p4_count = score_p4_count;
    }

    public Integer getScoreP3Count() {
        return score_p3_count;
    }

    public void setScoreP3Count(Integer score_p3_count) {
        this.score_p3_count = score_p3_count;
    }

    public Integer getScoreP2Count() {
        return score_p2_count;
    }

    public void setScoreP2Count(Integer score_p2_count) {
        this.score_p2_count = score_p2_count;
    }

    public Integer getScoreP1Count() {
        return score_p1_count;
    }

    public void setScoreP1Count(Integer score_p1_count) {
        this.score_p1_count = score_p1_count;
    }

    public Integer getScoreN1Count() {
        return score_n1_count;
    }

    public void setScoreN1Count(Integer score_n1_count) {
        this.score_n1_count = score_n1_count;
    }

    public Integer getScoreN2Count() {
        return score_n2_count;
    }

    public void setScoreN2Count(Integer score_n2_count) {
        this.score_n2_count = score_n2_count;
    }

    public Integer getScoreN3Count() {
        return score_n3_count;
    }

    public void setScoreN3Count(Integer score_n3_count) {
        this.score_n3_count = score_n3_count;
    }

    public Integer getScoreN4Count() {
        return score_n4_count;
    }

    public void setScoreN4Count(Integer score_n4_count) {
        this.score_n4_count = score_n4_count;
    }

    public Integer getScoreN5Count() {
        return score_n5_count;
    }

    public void setScoreN5Count(Integer score_n5_count) {
        this.score_n5_count = score_n5_count;
    }

    public Double getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(Double totalRank) {
        this.totalRank = totalRank;
    }
}