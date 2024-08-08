package com.example.nogiback.entity;

import java.util.Map;

public class MemberScoreAndRank {
    private Map<String, Integer> scores;
    private Double totalRank;

    public MemberScoreAndRank(Map<String, Integer> scores, Double totalRank) {
        this.scores = scores;
        this.totalRank = totalRank;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    public Double getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(Double totalRank) {
        this.totalRank = totalRank;
    }
}
