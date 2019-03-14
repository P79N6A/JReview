package com.example.java;

import com.aliyun.abs.common.acsclient.AcsClientWrapperBuilder;
import com.aliyun.abs.common.acsclient.AcsClientWrapperBuilderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
        AcsClientWrapperBuilder dcdnipa = AcsClientWrapperBuilderFactory.getInstance("dcdn");
    }

}
