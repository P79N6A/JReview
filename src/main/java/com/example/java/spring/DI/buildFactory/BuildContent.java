package com.example.java.spring.DI.buildFactory;

import com.example.java.spring.DI.buildFactory.BuildConfig;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

public class BuildContent {
    //构建对象的配置容器
    public static Map<String, Object> mapContent = new HashMap<>();

    @PostConstruct //容器初始化的时候加载配置容器
    public void initConfig() {
        new BuildConfig(mapContent);
        mapContent.forEach((x, v) -> System.out.println("Item : " + x + " Count : " + v));
    }

    public Object getInstance(String obj) {
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
