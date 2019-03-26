package com.example.java.JUC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * join 原理：在当前线程中调用另一个线程线程 thread 的 join() 方法时，会调用该 thread 的 wait() 方法，
 * 直到这个 thread 执行完毕(JVM在 run() 方法执行完后调用 exit() 方法，
 * 而 exit() 方法里调用了 notifyAll() 方法)会调用 notifyAll() 方法主动唤醒当前线程。
 * <p>
 * countDownLatch 原理：可以理解为一个计数器。在初始化 CountDownLatch 的时候会在类的内部初始化一个int的变量，
 * 每当调用 countDownt() 方法的时候这个变量的值减1，而 await() 方法就是去判断这个变量的值是否为0，是则表示所有的操作都已经完成，否则继续等待。
 * <p>
 * join和countDownLatch都能实现让当前线程阻塞等待其他线程执行完毕，join使用起来更简便，不过countDownLatch粒度更细。
 * 由于CountDownLatch需要开发人员很明确需要等待的条件，否则容易造成await()方法一直阻塞。
 */
public class CountDownLatchTest {
    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) {
        long sleepTime = 5000;
        try {
            TestJoinThread joinThread1 = new TestJoinThread("joinThread1", sleepTime);
            TestJoinThread joinThrad2 = new TestJoinThread("joinThrad2", sleepTime);
            joinThread1.start();
            joinThrad2.start();
            joinThread1.join();

            joinThrad2.join();
            logger.info("主线程开始运行...");
        } catch (InterruptedException e) {
            logger.error("test join err!", e);
        }

        try {
            CountDownLatch count = new CountDownLatch(2);
            TestCountDownLatchThread countDownLatchThread1 = new TestCountDownLatchThread(count, "countDownLatchThread1", sleepTime);
            TestCountDownLatchThread countDownLatchThread2 = new TestCountDownLatchThread(count, "countDownLatchThread2", sleepTime);
            countDownLatchThread1.start();
            countDownLatchThread2.start();
            count.countDown();//类似于通知await的作用，每调用一次，减一。等于0时通知await
            count.await(10, TimeUnit.SECONDS);  //主线程阻塞
            logger.info("主线程开始运行...");
        } catch (InterruptedException e) {
            logger.error("test countDownLatch err!", e);
        }
    }

    static class TestJoinThread extends Thread {

        private String threadName;
        private long sleepTime;

        public TestJoinThread(String threadName, long sleepTime) {
            this.threadName = threadName;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                logger.info(String.format("线程[%s]开始运行...", threadName));
                Thread.sleep(sleepTime);
                logger.info(String.format("线程[%s]运行结束 耗时[%s]s", threadName, sleepTime / 1000));
            } catch (Exception e) {
                logger.error("TestJoinThread run err!", e);
            }
        }
    }

    static class TestCountDownLatchThread extends Thread {

        private String threadName;
        private long sleepTime;
        private CountDownLatch countDownLatch;

        public TestCountDownLatchThread(CountDownLatch countDownLatch, String threadName, long sleepTime) {
            this.countDownLatch = countDownLatch;
            this.threadName = threadName;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                logger.info(String.format("线程[%s]第一阶段开始运行...", threadName));
                Thread.sleep(sleepTime);
                logger.info(String.format("线程[%s]第一阶段运行结束耗时[%s]s", threadName, sleepTime / 1000));
                countDownLatch.countDown();
                logger.info(String.format("线程[%s]第二阶段开始运行...", threadName));
                Thread.sleep(sleepTime);
                logger.info(String.format("线程[%s]第二阶段运行结束耗时[%s]s", threadName, sleepTime / 1000));
            } catch (Exception e) {
                logger.error("TestCountDownLatchThread run err!", e);
            }
        }
    }
}
