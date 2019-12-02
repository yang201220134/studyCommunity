package com.ysy.studycommunity.mapper;


import com.ysy.studycommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface QuestionMapper {
    @Insert("insert into question (title,description,creator,tag) values(#{title},#{description},#{creator},#{tag})")
    public void insert(Question question);

    //获取页面列表
    @Select("select * from question")
    public List<Question> getQuestionList();

}
