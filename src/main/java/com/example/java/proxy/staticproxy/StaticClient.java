package com.example.java.proxy.staticproxy;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class StaticClient {
    public static void main(String[] args) {
        StaticProxy proxy = new StaticProxy(new RealSubject());
        proxy.hello();
        proxy.reqeust();
    }
}
