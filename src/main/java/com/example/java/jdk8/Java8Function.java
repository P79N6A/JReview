package com.example.java.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class Java8Function {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("输出所有数据:");
        //传递参数
        eval(list, a -> true);
        System.out.println("输出所有偶数:");
        eval(list, a -> a % 2 == 0);
        System.out.println("输出所有奇数");
        eval(list, a -> a % 2 != 0);
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer x : list) {
            if (predicate.test(x)) {
                System.out.println(x + " ");
            }
        }

    }
}
