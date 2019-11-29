package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.dto.AccessTokenDTO;
import com.ysy.studycommunity.dto.GitHubUser;
import com.ysy.studycommunity.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private  String clientId;
    @Value("${github.client.secret}")
    private  String clientSecret;
    @Value("${github.redirect.url}")
    private  String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name="state")String state
                , HttpServletRequest request, Model model){
        System.out.println(clientId);
        System.out.println(clientSecret);
        System.out.println(redirectUrl);
        System.out.println("code:+"+code+";stade is:"+state);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println("accesstoken is"+accessToken);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        System.out.println(gitHubUser);

        if(gitHubUser!=null){
            //登录成功，写cookie和session
            request.getSession().setAttribute("user",gitHubUser);
            System.out.println("即将跳转不为空，已经设置了session");
            GitHubUser gitHubUser1 = (GitHubUser) request.getSession().getAttribute("user");
            System.out.println("login" +gitHubUser1.getLogin());
            model.addAttribute("muser",gitHubUser1.getLogin());
            return "redirect:index";
        }else {
            //登录失败，重新登录
            System.out.println("为空,返回登录页面");
            return "redirect:index";
        }



    }



}
