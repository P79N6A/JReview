package com.example.java.ThreadlocalUse.ticket;

import org.springframework.util.StopWatch;

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

    private static int ticket = 500000;
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        watch.start();
        MyThreadRunable runable = new MyThreadRunable();
        new Thread(runable,"窗口A").start();
        new Thread(runable,"窗口B").start();
        new Thread(runable,"窗口C").start();
        new Thread(runable,"窗口D").start();
        new Thread(runable,"窗口E").start();
        watch.stop();
        System.out.println(watch.getTotalTimeMillis()+"============");
    }

    @Override
    public void run() {

        while (true) {
            lock.lock();
            if (ticket > 0) {
                ticket--;
                ++count;
                System.out.println(Thread.currentThread().getName() + "卖出了" + count + "张票，剩余" + ticket + "张票");
            }
            lock.unlock();
        }
    }
}
