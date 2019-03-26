CyclicBarrier与CountDownLatch比较

    1）CountDownLatch:一个线程(或者多个)，等待另外N个线程完成某个事情之后才能执行；
        CyclicBarrier:N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
    2）CountDownLatch:一次性的；CyclicBarrier:可以重复使用。
    3）CountDownLatch基于AQS；CyclicBarrier基于锁和Condition。本质上都是依赖于volatile和CAS实现的。