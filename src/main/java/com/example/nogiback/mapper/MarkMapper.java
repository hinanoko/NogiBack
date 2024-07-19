package com.example.nogiback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.MarkMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface MarkMapper extends BaseMapper<MarkMember> {

    @Update("UPDATE markmember SET memberPosiMark = COALESCE(memberPosiMark, 0) + #{score} WHERE memberId = #{memberId}")
    int updateMemberPosiMark(@Param("memberId") Integer memberId, @Param("score") Integer score);

    @Update("UPDATE markmember SET memberNogiMark = COALESCE(memberNogiMark, 0) + #{score} WHERE memberId = #{memberId}")
    int updateMemberNogiMark(@Param("memberId") Integer memberId, @Param("score") Integer score);

    @Select("SELECT memberId, memberName, memberNogiMark, memberPosiMark FROM markmember")
    List<MarkMember> getAllMemberMarks();
}