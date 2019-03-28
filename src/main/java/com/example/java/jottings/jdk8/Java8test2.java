package com.example.java.jottings.jdk8;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class Java8test2 {

    final static String salutation = "Hello! ";

    public static void main(String args[]) {
        GreetingService tConsumer = message -> System.out.println("hello: " + message);
        tConsumer.sayMessage("world");

    }

    interface GreetingService {
        void sayMessage(String message);
    }
}
