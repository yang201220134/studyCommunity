package com.ysy.studycommunity.service;

import com.ysy.studycommunity.dto.QuestionDTO;
import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    List<QuestionDTO> getQuestionDTOList(){
        List<QuestionDTO> list = new ArrayList<>();

        List<Question> listQuestion = questionMapper.getQuestionList();

        for (Question question:listQuestion
             ) {

            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO

        }

        return
    }

}
