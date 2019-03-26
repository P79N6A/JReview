package com.example.java.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

public class DynamicProxyClient {

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Subject client = new DynamicProxy(new RealSubject()).getProxy();
        client.reqeust();
        client.hello();
    }
}
