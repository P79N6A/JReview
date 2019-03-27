package com.example.java.spring.DI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
	@Autowired
	AppServer server;
	
	@RequestMapping("/text")
	@ResponseBody
	public String text() {
		return server.send("hello");
	}
}
