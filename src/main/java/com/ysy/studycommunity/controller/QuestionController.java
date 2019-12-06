package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.dto.QuestionPageDTO;
import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.provider.SessionUserProvider;
import com.ysy.studycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private SessionUserProvider sessionUserProvider;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id, Model model, HttpServletRequest request){

        QuestionPageDTO questionPageDTO = new QuestionPageDTO();

        questionPageDTO.setQuestion(questionService.getOneQuestionById(id));
        questionPageDTO.setLoginUser(sessionUserProvider.getSessionUser(request));
        Integer user_id = questionPageDTO.getQuestion().getUser_id();
        questionPageDTO.setFawentiUser(questionService.getFawentiUser(user_id));



        model.addAttribute("questionPageDTO",questionPageDTO);
        return "question";
    }


}
