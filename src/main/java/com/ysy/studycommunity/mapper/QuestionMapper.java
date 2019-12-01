package com.ysy.studycommunity.mapper;


import com.ysy.studycommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface QuestionMapper {
    @Insert("insert into question (title,description,creator,tag) values(#{title},#{description},#{creator},#{tag})")
    public void insert(Question question);

}
