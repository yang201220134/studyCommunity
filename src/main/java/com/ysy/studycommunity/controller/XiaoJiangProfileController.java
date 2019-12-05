package com.ysy.studycommunity.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.User;
import com.ysy.studycommunity.service.ProfileService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class XiaoJiangProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfileService profileService;

    @Value("${page.size}")
    private Integer everyPageSize;

    @GetMapping("/profile/{action}/{currentPage}")
    public String profile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request,
                         @PathVariable(name="currentPage") Integer currentPage
                          ) {

// @RequestParam(defaultValue = "1")


        boolean islogin = false;
        User user = null;
        Cookie[] cookies = request.getCookies();

        if(cookies!=null){



            for(Cookie cookie :cookies){
                System.out.println("cookie key"+cookie.getName());
                System.out.println("cookie name"+cookie.getValue());
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    System.out.println("准备进数据库"+token);

                     user = userMapper.findByToken(token);
                    System.out.println("出数据库"+user);
                    if(user !=null){
                        request.getSession().setAttribute("user",user);
                        islogin = true;
                        System.out.println(user);
                        System.out.println("去数据库查找token"+user.getToken());

                    }

                    break;
                }
            }
            user = (User)request.getSession().getAttribute("user");
            if(user!=null){
                islogin = true;
            }


            if(islogin){
                return "redirect:/";
            }



        }

    System.out.println(currentPage+"-----------------------------------------");

      model.addAttribute("myQuestionPageDTO",profileService.getQuestionPageDTOList(user,currentPage,everyPageSize));



        if ("questions".equals(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");

            return "profile";
        }else
        if (action.equals("replies")) {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");

            return "profile";
        }

        return "profile";
    }
}