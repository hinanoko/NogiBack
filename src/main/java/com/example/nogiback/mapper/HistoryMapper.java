package com.example.nogiback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.BlogCount;
import com.example.nogiback.entity.Gamble;
import com.example.nogiback.entity.History;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    @Insert("INSERT INTO history (blogIdolId, blogUserId, blogDate) " +
            "VALUES (#{blogIdolId}, #{blogUserId}, #{blogDate})")
    @Options(useGeneratedKeys = true, keyProperty = "blogId")
    int insertNewBlog(History history);


    @Select("SELECT blogIdolId, COUNT(*) as blogCount " +
            "FROM history " +
            "WHERE blogIdolId BETWEEN '0001' AND '0032' " +
            "GROUP BY blogIdolId")
    List<BlogCount> countBlogsByIdolId();

    @Select("SELECT blogDate AS blogDate FROM history WHERE blogIdolId = #{idolId}")
    List<String> findBlogDatesByIdolId(@Param("idolId") String idolId);


    @Select("SELECT blogId FROM history WHERE blogIdolId = #{idolId}")
    List<Integer> findBlogIdsByIdolId(@Param("idolId") String idolId);
}
