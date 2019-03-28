package com.example.java.jottings.ThreadlocalUse;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.cnblogs.com/WangHaiMing/p/8798709.html
 *
 * remove 移除并返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
 * poll 移除并返问队列头部的元素 如果队列为空，则返回null
 * take 移除并返回队列头部的元素
 * add 增加一个元索 如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 * offer 添加一个元素并返回true 如果队列已满，则返回false
 * put 添加一个元素 如果队列满，则阻塞
 * element 返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
 * peek 返回队列头部的元素 如果队列为空，则返回null
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建队列
        BlockingQueue<String> bq = new LinkedBlockingDeque(10);

//        创建两个生产者
        ProducerThread p1 = new ProducerThread(bq);
        ProducerThread p2 = new ProducerThread(bq);

//        创建一个消费者
        consumerThread c1 = new consumerThread(bq);
//
        Thread t1 = new Thread(p1);
        t1.start();
        Thread t2 = new Thread(p2);
        t2.start();
        Thread t3 = new Thread(c1);
        t3.start();
        Thread.sleep(10 * 1000);
        p1.stopThread();
        c1.stopThread();
    }

}

/**
 * 消费者
 */
class consumerThread implements Runnable {
    private BlockingQueue deque;
    private volatile static boolean flag = true;
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public consumerThread(BlockingQueue deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        System.err.println("生产者启动。。。。。");
        try {
            while (flag) {
                System.err.println("生产者正在生产消息。。。");
                String count = String.valueOf(atomicInteger.incrementAndGet());
                //添加到队列数据
                boolean offer = deque.offer(count);
                if (offer) {
                    System.err.println("生产者添加队列" + count + "成功！");
                } else {
                    System.err.println("生产者添加队列" + count + "失败！");
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者线程结束。。。。");
        }
    }

    public void stopThread() {
        this.flag = false;
    }
}


/**
 * 生产者
 */
class ProducerThread implements Runnable {

    private BlockingQueue queue;
    private volatile static boolean flag = true;

    public ProducerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者线程启动。。。。。。。");
        try {
            while (flag) {
//                poll先进先出 pop先进后出
                //获取完毕后，队列会删除头元素
                String data = (String) queue.poll(2, TimeUnit.SECONDS);
                if (data != null) {
                    System.out.println("消费者获取data：" + data + "成功！");
                } else {
                    System.out.println("消费者获取data失败！");
                    this.flag = false;
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者结束。。。");
        }
    }

    public void stopThread() {
        this.flag = false;
    }
}