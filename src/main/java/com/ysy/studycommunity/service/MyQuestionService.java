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

    private  Integer userId;
    @Value("${page.size}")
    private Integer everyPageSize;


    public MyQuestionPageDTO getMyQuestionPageDTO(User user,Integer currentPage){
        userId = userMapper.findIdByUserName(user.getName());
        List<Question> questionList = questionMapper.getQuestionByUserId(userId,currentPage,this.everyPageSize);
        System.out.println("个人问题页当前页面service"+currentPage);
        MyQuestionPageDTO myQuestionPageDTO = new MyQuestionPageDTO();

        myQuestionPageDTO.setQuestionCount(questionMapper.getQuestionTotalByUserId(userId));

        myQuestionPageDTO.setQuestionList(questionList);

        myQuestionPageDTO.initPageData(currentPage,this.everyPageSize,user);

        return myQuestionPageDTO;
    }



}
