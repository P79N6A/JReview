package com.example.java.spring.service.impl;

import com.example.java.spring.service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public String sendMessage() {
        return "reqeustId : " + UUID.randomUUID();
    }
}
