package com.example.java.spring.DI.service.impl;

import com.example.java.spring.DI.service.ASCService;

public class ASCServiceImpl implements ASCService {
    @Override
    public String checkRAM() {
        return "OK,TRUE";
    }
}
