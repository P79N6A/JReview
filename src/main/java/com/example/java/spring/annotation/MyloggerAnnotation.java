package com.example.java.spring.annotation;

import com.example.java.jottings.cache.CacheableAnnotation.CacheAspect;
import com.example.java.jottings.cache.CacheableAnnotation.MyCacheAble;
import com.example.java.spring.controller.UserController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

@Aspect
@Component
public class MyloggerAnnotation {
    private static final Log log = LogFactory.getLog(MyloggerAnnotation.class);

    public MyloggerAnnotation() {
        log.info("====MyloggerAnnotation create====");
    }

    @Around("@annotation(mylogger)")
    public String resolutionBefore(final ProceedingJoinPoint pjp, Mylogger mylogger) {
        String str = null;
        try {
            log.info("开始测试方法------" + mylogger.name());
            str = (String) pjp.proceed();
            log.info("测试结束方法" + mylogger.name() + "--- successful ---");
        } catch (Throwable throwable) {
            log.info("测试结束方法" + mylogger.name() + "--- error ---");
            throwable.printStackTrace();
        }
        return str;
    }
}
