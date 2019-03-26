package com.example.java.ThreadlocalUse;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        //创建一个有界队列
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1000);
        // 创建一个生产者
        ProducerThreads p1 = new ProducerThreads(latch, queue);
        ProducerThreads p2 = new ProducerThreads(latch, queue);
        //穿件两个消费者
        consumerThreads c1 = new consumerThreads(latch, queue);
        consumerThreads c2 = new consumerThreads(latch, queue);
        //启动生产者和消费者
        p1.start();
        p2.start();
        c1.start();
        c2.start();

        //等待latch结束
        latch.await();
        System.err.println("main start  run ...");

    }
}


class ProducerThreads extends Thread {
    private volatile boolean flag = true;
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static BlockingQueue queue;
    private static CountDownLatch latch;

    public ProducerThreads(CountDownLatch latch, BlockingQueue queue) {
        this.queue = queue;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                //添加生产者
                boolean offer = queue.offer(atomicInteger.incrementAndGet() + "");
                if (offer) {
                    System.out.println("生产成功。。。");
                } else {
                    System.out.println("生产失败。。。");
                }
                Thread.sleep(100);
                if (atomicInteger.get() > 20) {
                    flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束。。。");
            latch.countDown();
        }
    }

    public void stopThread() {
        this.flag = false;
    }

}

class consumerThreads extends Thread {

    private volatile boolean flag = true;
    private static BlockingQueue queue;
    private static CountDownLatch latch;

    public consumerThreads(CountDownLatch latch, BlockingQueue queue) {
        this.queue = queue;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                String poll = (String) queue.poll(2, TimeUnit.SECONDS);
                if (poll != null) {
                    System.out.println("消费者成功" + poll);
                } else {
                    System.out.println("消费者失败。。。");
                    this.flag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者结束。。。");
            latch.countDown();
        }
    }
}