package com.example.nogiback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Insert("INSERT INTO comment (customerId, memberId, content, date) VALUES (#{customerId}, #{memberId}, #{content}, #{date})")
    void insertComment(Comment comment);

    @Select("SELECT * FROM comment WHERE memberId = #{memberId}")
    List<Comment> findCommentsByMemberId(@Param("memberId") int memberId);

}
