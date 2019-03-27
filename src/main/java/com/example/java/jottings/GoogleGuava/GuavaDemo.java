package com.example.java.jottings.GoogleGuava;

import com.google.common.base.Joiner;
import com.google.common.collect.*;

import java.util.*;

//Guava是对Java API的补充，对Java开发中常用功能进行更优雅的实现，使得编码更加轻松，代码容易理解。Guava使用了多种设计模式，同时经过了很多测试，得到了越来越多开发团队的青睐。Java最新版本的API采纳了Guava的部分功能，但依旧无法替代。
//*   高效设计良好的API，被Google的开发者设计，实现和使用
//*   遵循高效的java语法实践
//*   使代码更刻度，简洁，简单
//*   节约时间，资源，提高生产力  Guava工程包含了若干被Google的 Java项目广泛依赖 的核心库，例如：
//
//1.  集合 [collections]
//2.  缓存 [caching]
//3.  原生类型支持 [primitives support]
//4.  并发库 [concurrency libraries]
//5.  通用注解 [common annotations]
//6.  字符串处理 [string processing]
//7.  I/O 等等。
public class GuavaDemo {
    public static void main(String[] args) {
//        CollectionDemo();
//        StringJoiner();
        map2String();
    }

//    将Map转化为字符串
    private static void map2String() {
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Cookies", "12332");
        testMap.put("Content-Length", "30000");
        testMap.put("Date", "2018.07.04");
        testMap.put("Mime", "text/html");
        // 用:分割键值对，并用#分割每个元素，返回字符串
        String join = Joiner.on("#").withKeyValueSeparator(":").join(testMap);
        System.out.println(join);
    }

    private static void StringJoiner() {
        StringBuilder sb = new StringBuilder("hello : ");
        // 字符串连接器，以|为分隔符，同时去掉null元素
        Joiner joiner = Joiner.on("|").skipNulls();
        // 构成一个字符串jim|jack|kevin并添加到stringBuilder
        sb = joiner.appendTo(sb, "jim", "jack", null, "kevin");
        System.out.println(sb);
    }


    public static void CollectionDemo() {
        //    普通的Collection集合
        ArrayList<Object> list = Lists.newArrayList();
        HashMap<Object, Object> map = Maps.newHashMap();
        HashSet<Object> set = Sets.newHashSet(); //不允许出想重复
//        不可变Collection集合
        ImmutableBiMap<String, String> map1 = ImmutableBiMap.of("key", "123", "key1", "456");
        ImmutableSet<String> set1 = ImmutableSet.of("e1", "e2");
        ImmutableList<String> list1 = ImmutableList.of("e1");

//        Range range = Range.all(); //[(-∞‥+∞)]
        Range<Integer> range1 = Range.open(0, 23);// [(0‥23)]
        ImmutableRangeSet<Integer> set2 = ImmutableRangeSet.of(range1);
        System.out.println(set2);


//        map值为list装数据
        ArrayListMultimap<Object, Object> multimap = ArrayListMultimap.create();
        multimap.put("key", "list");
        multimap.put("key", "list2");
        System.out.println(multimap.get("key"));

    }

}
