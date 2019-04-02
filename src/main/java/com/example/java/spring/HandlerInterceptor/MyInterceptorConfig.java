package com.example.java.spring.HandlerInterceptor;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

/**
 * @Description: 配置拦截器
 * @author: czb
 * @date: 2019-04-02 11:45:47
 */
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> list = Lists.newArrayList();
        list.add("/login.html");
        list.add("/index.html");
        list.add("/home.html");
        registry.addInterceptor(new TestInterceptor()).addPathPatterns(list);
    }
}