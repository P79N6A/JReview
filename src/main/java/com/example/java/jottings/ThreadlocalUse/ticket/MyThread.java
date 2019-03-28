package com.example.java.jottings.ThreadlocalUse.ticket;


import javax.sound.midi.Soundbank;
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
    private volatile static boolean flag = true;
    private static int ticket = 500000;//模拟3个窗口卖火车票
    private static int count = 0;
    private static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        MyThread runable = new MyThread();

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Executors.newCachedThreadPool();
//        Executors.newScheduledThreadPool(2);
//        Executors.newSingleThreadExecutor();

        BlockingQueue queue = new ArrayBlockingQueue(3);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 0, TimeUnit.SECONDS, queue);
        threadPoolExecutor.execute(runable);
        threadPoolExecutor.execute(runable);
        threadPoolExecutor.execute(runable);
        latch.await();
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

        //优雅的关闭，用shutdown()
        //想立马关闭，并得到未执行任务列表，用shutdownNow()
        //优雅的关闭，并允许关闭声明后新任务能提交，用awaitTermination()
        threadPoolExecutor.shutdown();
        boolean terminated = threadPoolExecutor.isShutdown();
        System.out.println(terminated);
    }

    public void run() {
        while (flag) {
            synchronized (MyThread.class) {
                if (ticket > 0) {
                    ticket--;
                    count++;
                    System.out.println(Thread.currentThread().getName() + "卖出了" + count + "个票,还剩下" + ticket + "张票");
                } else {
                    latch.countDown();
                    flag = false;
                }
            }
        }
    }
}

