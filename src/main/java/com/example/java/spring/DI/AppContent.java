package com.example.java.spring.DI;

import java.util.HashMap;
import java.util.Map;

public class AppContent {
//	private String args;
//	public AppContent(String args) {
//		this.args=args;
//	}
	
	private Map<String, String> map = new HashMap<>();
	public AppContent initfig() {
		init1();
		init2();
		return this;
	}

	private void init2() {
		System.out.println("初始化应用参数");
	}

	private void init1() {
		map.put("hello", "hello");
	}
	
	//app方法
	public String go() {
		return map.get("hello");
	}
	
}
