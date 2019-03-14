package com.example.java;

import com.aliyun.abs.common.acsclient.AcsClientWrapperBuilder;
import com.aliyun.abs.common.acsclient.AcsClientWrapperBuilderFactory;
import com.aliyun.abs.common.service.impl.DescribeUserBusinessStatusImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaApplicationTests {

    @Test
    public void contextLoads() {
//        logger.info("map中是否加载dcdnipaAcsClientWrapperBuilder： " + acsClientWrapperMap.containsKey("dcdnipaAcsClientWrapperBuilder"));
//        System.out.println("========");
        AcsClientWrapperBuilder dcdnipa = AcsClientWrapperBuilderFactory.getInstance("dcdn");
        System.out.println(dcdnipa);
    }
}
