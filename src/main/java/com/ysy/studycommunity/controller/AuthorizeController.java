package com.ysy.studycommunity.controller;

import com.ysy.studycommunity.dto.AccessTokenDTO;
import com.ysy.studycommunity.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private  String clientId;
    @Value("${github.client.secret}")
    private  String clientSecret;
    @Value("$github.redirect.url}")
    private  String redirectUrl;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name="state")String state){



        System.out.println("code:+"+code+";stade is:"+state);

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);

        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);

        return "index";
    }



}
