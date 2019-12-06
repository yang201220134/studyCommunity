package com.ysy.studycommunity.service;

import com.ysy.studycommunity.dto.PageDTO;
import com.ysy.studycommunity.dto.QuestionDTO;
import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import com.ysy.studycommunity.provider.SessionUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SessionUserProvider sessionUserProvider;

    public List<QuestionDTO> getQuestionDTOList(HttpServletRequest request, Integer currentPage, Integer everyPageShowCount){
        List<QuestionDTO> listQuestionDTO = new ArrayList<>();
        Integer totalQuestionCount = questionMapper.questionCount();
        Integer totalPage = totalQuestionCount /everyPageShowCount + 1;
        if(currentPage > totalPage){
            currentPage = totalPage;
        }

        Integer offset = everyPageShowCount*(currentPage -1);



        List<Question> listQuestion = questionMapper.getQuestionList(offset,everyPageShowCount);


        for (Question question:listQuestion
             ) {

            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            User user = new User();
            user = userMapper.findUserById(question.getUser_id());
            questionDTO.setUser(user);
            listQuestionDTO.add(questionDTO);
        }

        return listQuestionDTO;
    }
        public Integer getQuestionCount(){
        return questionMapper.questionCount();
        }



        public Question getOneQuestionById(Integer id){


            Question question =  new Question();
            question = questionMapper.getQuestionById(id);



            return  question;

        }

        public User getFawentiUser(Integer id){
            return userMapper.findUserById(id);
        }





}
