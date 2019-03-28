package com.example.java.jottings.proxy.staticproxy;

public class StaticClient {
    public static void main(String[] args) {
        StaticProxy proxy = new StaticProxy(new RealSubject());
        proxy.hello();
        proxy.reqeust();
    }
}
