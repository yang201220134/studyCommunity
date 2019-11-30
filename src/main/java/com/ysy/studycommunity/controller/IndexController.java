package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.mapper.UserMapper;
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

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie :cookies){
            System.out.println("cookie key"+cookie.getName());
            System.out.println("cookie name"+cookie.getValue());
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();

                User user = userMapper.findByToken(token);
                if(user !=null){
                    request.getSession().setAttribute("user",user);
                    System.out.println("去数据库查找token"+user.getToken());
                }

                break;
            }
        }





        return "index";
    }

















    @PostMapping("/greet")
    public String greetPost(@RequestParam(name="name",defaultValue = "postwold",required = false) String name,Model model){
        model.addAttribute("name",name+"post");
        return "hello";
    }
}
