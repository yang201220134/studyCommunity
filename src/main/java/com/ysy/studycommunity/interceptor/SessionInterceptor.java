package com.ysy.studycommunity.interceptor;

import com.ysy.studycommunity.mapper.UserMapper;
import com.ysy.studycommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean islogin = false;
        User user = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {


            for (Cookie cookie : cookies) {
                System.out.println("cookie key" + cookie.getName());
                System.out.println("cookie name" + cookie.getValue());
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    System.out.println("准备进数据库" + token);

                    user = userMapper.findByToken(token);
                    System.out.println("出数据库" + user);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        islogin = true;
                        System.out.println(user);
                        System.out.println("去数据库查找token" + user.getToken());

                    }

                    break;
                }
            }}
            user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                islogin = true;
            }

            System.out.println(islogin);

            if (!islogin) {
                System.out.println("因为没有登录 开始跳转到登录");
              //  request.getRequestDispatcher("/login");
               response.sendRedirect("http://localhost:9999/");
                return true;
            }

//        if(1==2){
//            System.out.println("1==1");
//            return false;
//        }
//        if(1==1){
//            System.out.println("1!=1");
//            return true;
//        }

            return true;
        }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}