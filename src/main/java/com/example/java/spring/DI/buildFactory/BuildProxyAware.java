package com.example.java.spring.DI.buildFactory;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BuildProxyAware implements InvocationHandler {

    private BuildFactoryAware obj;

    public BuildProxyAware(BuildFactoryAware obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object subject = null;
        try {
            subject = method.invoke(obj, args);
        } catch (Exception e) {
            throw e;
        }
        return subject;
    }

    /**
     * 获取被代理接口实例对象
     *
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
}
