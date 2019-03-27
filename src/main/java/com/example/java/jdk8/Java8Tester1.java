package com.example.java.jdk8;

import sun.awt.datatransfer.DataTransferer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class Java8Tester1 {
    public static void main(String[] args) {
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        Java8Tester1 tester = new Java8Tester1();
        tester.sortUsingJava8(names1);
        System.out.println(names1);

    }

    private void sortUsingJava8(List<String> names1) {
        Collections.sort(names1,(s1,s2) -> s1.compareTo(s2));
    }

}
