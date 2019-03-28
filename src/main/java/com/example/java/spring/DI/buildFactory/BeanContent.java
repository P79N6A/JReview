package com.example.java.spring.DI.buildFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanContent {
    ConcurrentHashMap<String, Object> mapContent = new ConcurrentHashMap<>();

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
