package com.ysy.studycommunity.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin**");
       // registry.addInterceptor().addPathPatterns("");

        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin**");


    }
}
