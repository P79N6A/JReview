package com.example.java.spring.DI.service.impl;

import com.example.java.spring.DI.service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public String sendMessage() {
        return "reqeustId : " + UUID.randomUUID();
    }
}
