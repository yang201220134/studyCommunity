package com.ysy.studycommunity.provider;

import com.ysy.studycommunity.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SessionUserProvider {

    public User getSessionUser(HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");
        return user;
    }
}
