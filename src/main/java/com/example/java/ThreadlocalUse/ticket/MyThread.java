package com.example.java.ThreadlocalUse.ticket;


import org.springframework.util.StopWatch;

import java.nio.file.WatchEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @描述
 * @参数 $params$
 * @返回值 $return$
 * @创建人 czb
 * @创建时间 $date$
 * @修改人和其它信息
 */
public class MyThread implements Runnable {

    private static int ticket = 500000000;//模拟3个窗口卖火车票

    private static int count = 0;

    public static void main(String[] args) {

        MyThread runable = new MyThread();

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//        Executors.newCachedThreadPool();
//        Executors.newScheduledThreadPool(2);
//        Executors.newSingleThreadExecutor();

        BlockingQueue queue = new ArrayBlockingQueue(3);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 0, TimeUnit.SECONDS, queue);
        threadPoolExecutor.execute(runable);
        threadPoolExecutor.execute(runable);
        threadPoolExecutor.execute(runable);


//        executorService.execute(runable);
//        executorService.execute(runable);
//        executorService.execute(runable);
//        executorService.execute(runable);
    }

    public void run() {
        while (true) {

            synchronized (MyThread.class) {
                if (ticket > 0) {
                    ticket--;
                    count++;
                    System.out.println(Thread.currentThread().getName() + "卖出了" + count + "个票,还剩下" + ticket + "张票");
                }
            }

//            try {
//                Thread.sleep(20000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

