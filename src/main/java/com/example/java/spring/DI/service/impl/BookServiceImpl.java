package com.example.java.spring.DI.service.impl;

import com.example.java.spring.DI.service.BookService;

public class BookServiceImpl implements BookService {
    @Override
    public String study() {
        return "i love study！";
    }
}
