package com.example.java.jottings.proxy.staticproxy;


public class RealSubject implements Subject {
    @Override
    public void reqeust() {
        System.out.println("static ----- request ");
    }

    @Override
    public void hello() {
        System.out.println("static ----- hello ");
    }
}
