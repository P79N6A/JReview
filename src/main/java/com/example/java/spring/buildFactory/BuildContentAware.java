package com.example.java.spring.buildFactory;

import com.example.java.spring.annotation.Mylogger;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class BuildContentAware {
    @Mylogger(name = "动态代理")
    @PostConstruct
    public void init() {
        BuildProxyAware buildProxyAware = new BuildProxyAware(new BuildFactoryAware());
        Object proxy = buildProxyAware.getProxy();
        Aware aware = (Aware) proxy;
        aware.init();
        System.out.println(
                "======动态代理的作用就是在不修改当前类的情况下添加新得功能。。。。\n" +
                "======常用在加载驱动。。。。\n" +
                "======保证业务代码的整洁。。。。\n" +
                "======无侵入式开发。。。。\n" +
                "======spring使用aop代替了jdk动态代理，其实内部也是cglib或者jdk动态代理。。。");
    }

}
