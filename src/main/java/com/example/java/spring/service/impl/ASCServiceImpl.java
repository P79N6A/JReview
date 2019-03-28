package com.example.java.spring.service.impl;

import com.example.java.spring.service.ASCService;

public class ASCServiceImpl implements ASCService {
    @Override
    public String checkRAM() {
        return "OK,TRUE";
    }
}
