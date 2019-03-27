package com.example.java.dev.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RAM implements ApplicationContextAware {

    //将BeanFactory容器以成员变量保存
    private ApplicationContext ctx;

    /**
     * 实现ApplicationContextAware接口实现的方法
     */
    @Override
    public void setApplicationContext(ApplicationContext cyx)
            throws BeansException {
        this.ctx = ctx;
    }

    //获取ApplicationContext的测试方法
    public ApplicationContext getContext() {
        return ctx;
    }

    public boolean getStatus() {
        return true;
    }

}
