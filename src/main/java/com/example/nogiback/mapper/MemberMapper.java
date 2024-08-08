package com.example.nogiback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.RankMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;


@Mapper
public interface MemberMapper extends BaseMapper<RankMember> {

    @Update({
            "<script>",
            "UPDATE rankmember",
            "<choose>",
            "<when test='score == 5'>SET score_p5_count = score_p5_count + 1</when>",
            "<when test='score == 4'>SET score_p4_count = score_p4_count + 1</when>",
            "<when test='score == 3'>SET score_p3_count = score_p3_count + 1</when>",
            "<when test='score == 2'>SET score_p2_count = score_p2_count + 1</when>",
            "<when test='score == 1'>SET score_p1_count = score_p1_count + 1</when>",
            "<when test='score == -1'>SET score_n1_count = score_n1_count + 1</when>",
            "<when test='score == -2'>SET score_n2_count = score_n2_count + 1</when>",
            "<when test='score == -3'>SET score_n3_count = score_n3_count + 1</when>",
            "<when test='score == -4'>SET score_n4_count = score_n4_count + 1</when>",
            "<when test='score == -5'>SET score_n5_count = score_n5_count + 1</when>",
            "</choose>",
            "WHERE rankmemberid = #{memberId}",
            "</script>"
    })
    void incrementScoreCount(@Param("memberId") int memberId, @Param("score") int score);

    @Update({
            "<script>",
            "UPDATE rankmember",
            "<choose>",
            "<when test='score == 5'>SET score_p5_count = score_p5_count - 1</when>",
            "<when test='score == 4'>SET score_p4_count = score_p4_count - 1</when>",
            "<when test='score == 3'>SET score_p3_count = score_p3_count - 1</when>",
            "<when test='score == 2'>SET score_p2_count = score_p2_count - 1</when>",
            "<when test='score == 1'>SET score_p1_count = score_p1_count - 1</when>",
            "<when test='score == -1'>SET score_n1_count = score_n1_count - 1</when>",
            "<when test='score == -2'>SET score_n2_count = score_n2_count - 1</when>",
            "<when test='score == -3'>SET score_n3_count = score_n3_count - 1</when>",
            "<when test='score == -4'>SET score_n4_count = score_n4_count - 1</when>",
            "<when test='score == -5'>SET score_n5_count = score_n5_count - 1</when>",
            "</choose>",
            "WHERE rankmemberid = #{memberId}",
            "</script>"
    })
    void decrementScoreCount(@Param("memberId") int memberId, @Param("score") int score);


    @Update("UPDATE rankmember SET totalrank = #{totalRank} WHERE rankmemberid = #{memberId}")
    void updateTotalRank(@Param("memberId") int memberId, @Param("totalRank") double totalRank);

    @Select({
            "SELECT score_p5_count, score_p4_count, score_p3_count, score_p2_count, score_p1_count, ",
            "score_n1_count, score_n2_count, score_n3_count, score_n4_count, score_n5_count ",
            "FROM rankmember WHERE rankmemberid = #{memberId}"
    })
    Map<String, Integer> getScores(@Param("memberId") int memberId);

    @Select("SELECT totalrank FROM rankmember WHERE rankmemberid = #{memberId}")
    Double getTotalRank(@Param("memberId") int memberId);


}
