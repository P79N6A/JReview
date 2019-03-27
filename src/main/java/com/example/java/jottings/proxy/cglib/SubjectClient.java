package com.example.java.jottings.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class SubjectClient {
    public static void main(String[] args) {
        SubjectProxy subjectProxy = new SubjectProxy();
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(subjectProxy);
        RealSubject realSbject = (RealSubject) enhancer.create();


        realSbject.lookup();
        realSbject.goup();
    }
}
