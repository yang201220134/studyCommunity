package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){
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

        List<Question> questionList = QuestionMapper.getQuestionList();


        return "index";
    }

















    @PostMapping("/greet")
    public String greetPost(@RequestParam(name="name",defaultValue = "postwold",required = false) String name,Model model){
        model.addAttribute("name",name+"post");
        return "hello";
    }
}
