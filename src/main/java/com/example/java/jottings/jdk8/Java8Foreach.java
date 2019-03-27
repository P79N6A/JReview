package com.example.java.jottings.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class Java8Foreach {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Google","Tuite","Tenxun","Alibaba","lenovo");
        names.forEach(s-> System.out.println(s));
        names.forEach(System.out::println);
    }
}
