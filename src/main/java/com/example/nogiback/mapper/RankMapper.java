package com.example.nogiback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.CustomerRank;
import org.apache.ibatis.annotations.*;


@Mapper
public interface RankMapper extends BaseMapper<CustomerRank> {

    @Insert("INSERT INTO rankrating (customer_id, member_id, ismark, membermark) VALUES (#{customerId}, #{memberId}, true, #{memberMark})")
    void insertRankRating(@Param("customerId") int customerId, @Param("memberId") int memberId, @Param("memberMark") int memberMark);

    @Update("UPDATE rankrating SET membermark = #{memberMark} WHERE customer_id = #{customerId} AND member_id = #{memberId}")
    void updateRankRating(@Param("customerId") int customerId, @Param("memberId") int memberId, @Param("memberMark") int memberMark);

    @Select("SELECT COUNT(*) > 0 FROM rankrating WHERE customer_id = #{customerId} AND member_id = #{memberId}")
    boolean existsRankRating(@Param("customerId") int customerId, @Param("memberId") int memberId);

    @Select("SELECT membermark FROM rankrating WHERE customer_id = #{customerId} AND member_id = #{memberId}")
    Integer getMemberMark(@Param("customerId") int customerId, @Param("memberId") int memberId);
}
