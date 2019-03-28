package com.example.java.jottings.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private RealSubject realSubject;

    public DynamicProxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamicProxy exection  before");
        Object subject = null;
        try {
            subject = method.invoke(realSubject, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            System.out.println("dynamicProxy exection after");
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
        return (T) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), this);
    }
}
