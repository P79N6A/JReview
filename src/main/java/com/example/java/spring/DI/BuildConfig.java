package com.example.java.spring.DI;

import java.util.Map;

public class BuildConfig {

	private AppContent appContent;
	
	//構建工具替你创建后放到map容器中
	public static BuildConfig newBuilder(Map<String, Object> mapContent) {
		BuildConfig buildConfig = new BuildConfig();
		AppContent app = new AppContent();
		buildConfig.appContent = app;
		mapContent.put("AppContent",app);
		return buildConfig;
	}

	//容器进行初始化一些参数
	public AppContent build() {
		return appContent.initfig();
	}
	
}
