package com.example.java.spring.DI;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

public class BuildContent {
	//构建对象的配置容器
	public static Map<String, Object> mapContent = new HashMap<>();
	
	@PostConstruct //容器初始化的时候加载配置容器
	public void initConfig() {
		 BuildConfig.newBuilder(mapContent).build();
	}
}
