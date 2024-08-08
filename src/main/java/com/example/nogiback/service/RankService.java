package com.example.nogiback.service;

import com.example.nogiback.entity.MemberScoreAndRank;
import com.example.nogiback.mapper.MemberMapper;
import com.example.nogiback.mapper.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RankService {

    private final RankMapper rankMapper;
    private final MemberMapper memberMapper;

    @Autowired
    public RankService(RankMapper rankMapper, MemberMapper memberMapper) {
        this.rankMapper = rankMapper;
        this.memberMapper = memberMapper;
    }

    public String addRating(int customerId, int memberId, int memberMark) {
        try {
            boolean exists = rankMapper.existsRankRating(customerId, memberId);

            if (exists) {
                int oldScore = rankMapper.getMemberMark(customerId, memberId);
                rankMapper.updateRankRating(customerId, memberId, memberMark);
                memberMapper.incrementScoreCount(memberId, memberMark);
                memberMapper.decrementScoreCount(memberId, oldScore);
            } else {
                rankMapper.insertRankRating(customerId, memberId, memberMark);
                memberMapper.incrementScoreCount(memberId, memberMark);
            }

            double newTotalRank = calculateTotalRank(memberId);
            memberMapper.updateTotalRank(memberId, newTotalRank);

            return "Rating added successfully.";
        } catch (Exception e) {
            return "Failed to add rating: " + e.getMessage();
        }
    }

    private double calculateTotalRank(int memberId) {
        // 获取该成员的所有分数及其对应的数量
        Map<String, Integer> scores = memberMapper.getScores(memberId);
        double totalRank = 0.0;
        int totalVotes = 0;

        System.out.println(scores);

        // 遍历每一个分数及其数量
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String key = entry.getKey();
            Integer count = entry.getValue();
            int score = extractScoreFromKey(key); // 提取分数

            // 计算总分，注意正负分数
            totalRank += score * count;
            totalVotes += count;
        }

        // 返回加权平均值
        return totalVotes == 0 ? 0.0 : totalRank / totalVotes;
    }

    private int extractScoreFromKey(String key) {
        // Check the prefix to determine the sign
        boolean isNegative = key.startsWith("score_n");

        // Extract the numeric part from the key
        String scoreStr = key.replaceAll("[^\\d]", ""); // Remove all non-digit characters

        // Parse the numeric part as an integer
        int score = Integer.parseInt(scoreStr);

        // Apply the negative sign if necessary
        return isNegative ? -score : score;
    }



    public MemberScoreAndRank getMemberScoresAndRank(int memberId) {
        Map<String, Integer> scores = memberMapper.getScores(memberId);
        Double totalRank = memberMapper.getTotalRank(memberId);
        return new MemberScoreAndRank(scores, totalRank);
    }
}
