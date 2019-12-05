package com.ysy.studycommunity.mapper;


import com.ysy.studycommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface QuestionMapper {
    @Insert("insert into question (gmt_create,gmt_modified,title,description,creator,tag,user_id) values(#{gmt_create},#{gmt_modified},#{title},#{description},#{creator},#{tag},#{user_id})")
    public void insert(Question question);

    //获取页面列表
    @Select("select * from question limit #{offset},#{everyPageShowCount}")
    public List<Question> getQuestionList(Integer offset,Integer everyPageShowCount);


    @Select("select count(*) from question")
    Integer questionCount();


    @Select("select * from question where user_id = #{id} limit #{offset},#{everyPageShowCount}")
    List<Question> getQuestionByUserId(Integer id,Integer offset,Integer everyPageShowCount);

    @Select("select count(*) from question where user_id = #{id}")
    Integer getQuestionTotalByUserId(Integer id);

    @Select("select count(*) from question where user_id = #{userId}")
    Integer getProfileQuestionSum (Integer userId);

    @Select("select * from question where user_id = #{userId} limit #{offset},#{size}")
    List<Question> getProfileQuestionList(Integer userId,Integer offset,Integer size);


}
