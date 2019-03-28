package com.example.java.jottings.jdk8;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class Java8test3 {
    public static void main(String args[]) {
        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(param + num);
        s.exec(3);  // 输出结果为 3
    }

}

interface Converter<T1, T2> {
    void exec(int i);
}
