package com.example.java.jottings.ThreadlocalUse.ticket;

import org.springframework.util.StopWatch;

import java.awt.print.Book;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class MyThreadRunable implements Runnable {

    private volatile static boolean flag = true;
    private static int ticket = 50;
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        watch.start();
        MyThreadRunable runable = new MyThreadRunable();
        Thread t1 = new Thread(runable, "窗口A");
        Thread t2 = new Thread(runable, "窗口B");
        Thread t3 = new Thread(runable, "窗口C");
        t1.start();
        t2.start();
        t3.start();
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
    }

    @Override
    public void run() {

        while (flag) {
            lock.lock();
            if (ticket > 0) {
                ticket--;
                ++count;
                System.out.println(Thread.currentThread().getName() + "卖出了" + count + "张票，剩余" + ticket + "张票");
            } else {
                flag = false;
            }
            lock.unlock();
        }
    }
}
