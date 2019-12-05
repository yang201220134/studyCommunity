package com.ysy.studycommunity.interceptor;

import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if(cookies!=null){

            for(Cookie cookie :cookies) {
                System.out.println("cookie key" + cookie.getName());
                System.out.println("cookie name" + cookie.getValue());
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    System.out.println("准备进数据库" + token);

                    User user = userMapper.findByToken(token);
                    System.out.println("出数据库" + user);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        System.out.println(user);
                        System.out.println("去数据库查找token" + user.getToken());

                    }

                    break;
                }
            }}



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
