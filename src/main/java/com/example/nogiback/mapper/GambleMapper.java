package com.example.nogiback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nogiback.entity.Gamble;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GambleMapper extends BaseMapper<Gamble> {

    @Select("SELECT COUNT(*) FROM gamble WHERE userName = #{userName}")
    int countByMemberName(@Param("userName") String userName);

    @Update("UPDATE gamble SET determine = #{gamble.determine}, thirdRowNum = #{gamble.thirdRowNum}, " +
            "secondRowNum = #{gamble.secondRowNum}, firstRowNum = #{gamble.firstRowNum}, " +
            "thirdRowMember = #{gamble.thirdRowMember}, secondRowMember = #{gamble.secondRowMember}, " +
            "firstRowMember = #{gamble.firstRowMember}, senBatuC = #{gamble.senBatuC}, " +
            "underC = #{gamble.underC}, title = #{gamble.title}, date = #{gamble.date} WHERE userName = #{userName}")
    int updateByUserName(@Param("userName") String userName, @Param("gamble") Gamble gamble);

    @Insert("INSERT INTO gamble (userName, determine, thirdRowNum, secondRowNum, firstRowNum, " +
            "thirdRowMember, secondRowMember, firstRowMember, senBatuC, underC, title, date) " +
            "VALUES (#{gamble.userName}, #{gamble.determine}, #{gamble.thirdRowNum}, #{gamble.secondRowNum}, " +
            "#{gamble.firstRowNum}, #{gamble.thirdRowMember}, #{gamble.secondRowMember}, #{gamble.firstRowMember}, " +
            "#{gamble.senBatuC}, #{gamble.underC}, #{gamble.title}, #{gamble.date})")
    int insertGamble(@Param("gamble") Gamble gamble);


    @Select("SELECT * FROM gamble WHERE userName = #{userName}")
    Gamble selectByUserName(@Param("userName") String userName);
}
