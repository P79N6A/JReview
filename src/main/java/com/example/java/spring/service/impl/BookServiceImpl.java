package com.example.java.spring.service.impl;

import com.example.java.spring.service.BookService;

public class BookServiceImpl implements BookService {
    @Override
    public String study() {
        return "i love study！";
    }
}
