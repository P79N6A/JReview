package com.example.java.spring.DI;

import org.springframework.stereotype.Service;

@Service
public class AppServer extends BuildContent {
	
	public String send(String string) {
		AppContent app = (AppContent)mapContent.get("AppContent");
		return app.go();
	}
}
