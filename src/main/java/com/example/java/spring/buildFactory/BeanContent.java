package com.example.java.spring.buildFactory;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanContent {
    ConcurrentHashMap<String, Object> mapContent = new ConcurrentHashMap<>();
    ArrayList listFile = Lists.newArrayList();

    default Object getInstance(String obj) {
        Object o = null;
        try {
            o = mapContent.get(obj);
            if (o == null) {
                throw new NullPointerException(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
