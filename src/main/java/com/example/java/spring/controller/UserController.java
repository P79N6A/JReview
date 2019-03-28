package com.example.java.spring.controller;

import com.example.java.spring.annotation.Mylogger;
import com.example.java.spring.buildFactory.BeanContent;
import com.example.java.spring.buildFactory.UseContentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements BeanContent {

    @Autowired
    UseContentBean useContentBean;

    @Mylogger(name = "text")
    @RequestMapping("/search")
    public String text() {
        return useContentBean.ascService.checkRAM() + " === \n"
                + useContentBean.serviceImpl.sendMessage() + "=== \n "
                + useContentBean.bookService.study();
    }
}
