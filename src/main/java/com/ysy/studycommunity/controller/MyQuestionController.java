package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.dto.MyQuestionPageDTO;
import com.ysy.studycommunity.model.User;
import com.ysy.studycommunity.service.MyQuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyQuestionController {

    @Autowired
    private MyQuestionService myQuestionService;

    @RequestMapping("/myquestion")
    public String myQuestionPage(Model model, HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(defaultValue ="1",name = "currentPage") Integer currentPage){

        User user = (User)request.getSession().getAttribute("user");
        if(user ==null){
            model.addAttribute("error","用户未登录");
            return "/";
        }
        MyQuestionPageDTO myQuestionPageDTO = myQuestionService.getMyQuestionPageDTO(user,currentPage);

        model.addAttribute("myQuestionPageDTO",myQuestionPageDTO);

        return "myquestion";
    }













}
