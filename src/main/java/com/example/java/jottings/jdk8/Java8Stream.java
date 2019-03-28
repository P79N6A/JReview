package com.example.java.jottings.jdk8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class Java8Stream {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> stream = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        stream.forEach(System.out::println);

        System.out.println("======forEach==========");
        Random random = new Random();
        random.ints().limit(1).forEach(System.out::println);

        System.out.println("========map==========");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

        System.out.println("========filter==========");
        List<String> str = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count = str.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        System.out.println("========limit==========");
        Random randos = new Random();
        randos.ints().limit(1).forEach(System.out::println);


        System.out.println("========sorted==========");
        Random r = new Random();
        r.ints().limit(10).sorted().forEach(System.out::println);

        System.out.println("=======并行（parallel）程序=====");
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count1 = list.parallelStream().filter(l -> l.isEmpty()).count();
        System.out.println(count1);


        System.out.println("Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：");
        List<String> strs = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        System.out.println("============统计=============");
        List<Integer> number = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
