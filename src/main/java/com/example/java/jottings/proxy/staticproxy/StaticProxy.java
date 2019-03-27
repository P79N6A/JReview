package com.example.java.jottings.proxy.staticproxy;

public class StaticProxy implements Subject {

    private RealSubject realSubject;

    public StaticProxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void reqeust() {
        realSubject.reqeust();
    }

    @Override
    public void hello() {
        realSubject.hello();
    }
}
