package com.example.java.jottings.proxy.dynamicproxy;

public class DynamicProxyClient {

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Subject client = new DynamicProxy(new RealSubject()).getProxy();
        client.reqeust();
        client.hello();
        client.hhhh();
    }
}
