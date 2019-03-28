package com.example.java.spring.DI.controller;

import com.example.java.spring.DI.buildFactory.BeanContent;
import com.example.java.spring.DI.buildFactory.UseContentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements BeanContent {

    @Autowired
    UseContentBean useContentBean;

    @RequestMapping("/search")
    public String text() {
        return useContentBean.ascService.checkRAM() + " === " + useContentBean.serviceImpl.sendMessage() + " ------  " + useContentBean.bookService.study();
    }
}
