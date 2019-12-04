package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.dto.PageDTO;
import com.ysy.studycommunity.dto.QuestionDTO;
import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.User;
import com.ysy.studycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @Value("${page.size}")
    private Integer everyPageShowCount;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(name="currentPage",defaultValue = "1")Integer currentPage
                        ){

        Cookie[] cookies = request.getCookies();

        if(cookies!=null){



        for(Cookie cookie :cookies){
            System.out.println("cookie key"+cookie.getName());
            System.out.println("cookie name"+cookie.getValue());
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                System.out.println("准备进数据库"+token);

                User user = userMapper.findByToken(token);
                System.out.println("出数据库"+user);
                if(user !=null){
                    request.getSession().setAttribute("user",user);
                    System.out.println(user);
                    System.out.println("去数据库查找token"+user.getToken());

                }

                break;
            }
        }


        }

       // List<Question> questionList = questionMapper.getQuestionList();
        List<QuestionDTO> questionDTOListData = questionService.getQuestionDTOList(request,currentPage,everyPageShowCount);


       // for (QuestionDTO questionDTOListDatum : questionDTOListData) {
       //     questionDTOListDatum.getQuestion().setDescription("resert");
       // }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOListData);
        pageDTO.setQuestionCount(questionMapper.questionCount());

        pageDTO.initPageData(currentPage,everyPageShowCount);

        System.out.println("currentPage--------------"+currentPage);

        model.addAttribute("pageDTO",pageDTO);


        return "index";
    }

















    @PostMapping("/greet")
    public String greetPost(@RequestParam(name="name",defaultValue = "postwold",required = false) String name,Model model){
        model.addAttribute("name",name+"post");
        return "hello";
    }
}
