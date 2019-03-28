package com.example.java.spring.DI.buildFactory;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BuildContentAware implements Aware {
    @PostConstruct
    public void init() {
        BuildProxyAware buildProxyAware = new BuildProxyAware(new BuildFactoryAware());
        Object proxy = buildProxyAware.getProxy();
        Aware aware = (Aware) proxy;
        aware.init();
    }
}
