package com.example.java.spring.buildFactory;

import com.example.java.spring.service.ASCService;
import com.example.java.spring.service.BookService;
import com.example.java.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UseContentBean implements BeanContent {
    public UserService serviceImpl = (UserService) getInstance("UserService");
    public ASCService ascService = (ASCService) getInstance("ASCService");
    public BookService bookService = (BookService) getInstance("BookService");
}
