package com.example.java.spring.DI.buildFactory;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SubjectProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        methodProxy.invokeSuper(o, objects);
        return o;
    }

}