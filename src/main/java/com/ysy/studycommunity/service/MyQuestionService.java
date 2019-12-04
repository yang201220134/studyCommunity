package com.ysy.studycommunity.service;

import com.ysy.studycommunity.dto.MyQuestionPageDTO;
import com.ysy.studycommunity.dto.QuestionDTO;
import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyQuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Value("${page.size}")
    private Integer everyPageSize;
    public List<Question> getOneUserQuestionList(User user){
        Integer userId = userMapper.findIdByUserName(user.getName());
        return questionMapper.getQuestionByUserId(userId);
    }


    public MyQuestionPageDTO getMyQuestionPageDTO(User user,Integer currentPage){
        MyQuestionPageDTO myQuestionPageDTO = new MyQuestionPageDTO();
        myQuestionPageDTO.setQuestionList(getOneUserQuestionList(user));
        myQuestionPageDTO.initPageData(currentPage,everyPageSize);



        return myQuestionPageDTO;
    }



}
