package com.example.java.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import javax.jws.Oneway;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubjectProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before Method Invoke");
        methodProxy.invokeSuper(o, objects);
        System.out.println("After Method Invoke");
        return o;
    }

}
