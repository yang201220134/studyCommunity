package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController
{
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(HttpServletRequest request, HttpServletResponse response, Model model){

       User user =  (User) request.getSession().getAttribute("user");
       if(user==null){
           model.addAttribute("error","用户没有登录，不能发布提问");
           return "index";
       }

        return "publish";
    }
    @RequestMapping("/saveQuestion")
    public String saveQuestion(HttpServletRequest request, String title,String tag,String description,Model model){

        Cookie[] cookies = request.getCookies();
        User user = null;
        for(Cookie cookie :cookies){
            System.out.println("cookie key"+cookie.getName());
            System.out.println("cookie name"+cookie.getValue());
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                System.out.println("准备进数据库"+token);
                user =new User();
                user = userMapper.findByToken(token);
                System.out.println("出数据库"+user);
                if(user !=null){
                    request.getSession().setAttribute("user",user);
                    System.out.println(user);
                    System.out.println("去数据库查找token"+user.getToken());

                }

                break;
            }
        }
        if(user ==null){
            model.addAttribute("error","用户未登录");
        }



        Question question = new Question();
        question.setDescription(description);
        question.setTag(tag);
        question.setTitle(title);
        String creator = "ysy";
        if(request.getSession().getAttribute("user")!=null
        ){
            User userlogin = (User) request.getSession().getAttribute("user");
            creator = userlogin.getName();
        }
        question.setCreator(creator);
        questionMapper.insert(question);
        return "index";
    }
    @RequestMapping("/saveQuestionObject")
    public String saveQuestionObject(HttpServletRequest request,Question question,Model model){
        Cookie[] cookies = request.getCookies();
        User user = null;
        for(Cookie cookie :cookies){
            System.out.println("cookie key"+cookie.getName());
            System.out.println("cookie name"+cookie.getValue());
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                System.out.println("准备进数据库"+token);
                user =new User();
                user = userMapper.findByToken(token);
                System.out.println("出数据库"+user);
                if(user !=null){
                    request.getSession().setAttribute("user",user);
                    System.out.println(user);
                    System.out.println("去数据库查找token"+user.getToken());

                }

                break;
            }
        }
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());

        if(user ==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        System.out.println("用户登录是否为空"+user);
        if(question.getTitle()==null||question.getTitle()==""){
            model.addAttribute("jiaoyan","标题不能为空");
            return "publish";
        }
        if(question.getDescription()==null||question.getDescription()==""){
            model.addAttribute("jiaoyan","问题描述不能为空");
            return "publish";
        }

        if(question.getTag()==null||question.getTag()==""){
            model.addAttribute("jiaoyan","标签不能为空");
            return "publish";
        }


        long date = System.currentTimeMillis();

        question.setGmt_modified(date);
        questionMapper.insert(question);
        return "redirect:/";
    }
}
