**1**、**创建线程的方式及实现**

    Java使用Thread类代表线程，所有的线程对象都必须是Thread类或其子类的实例。Java可以用三种方式来创建线程，如下所示：
    1）继承Thread类创建线程
    2）实现Runnable接口创建线程
    3）使用Callable和Future创建线程 
        和Runnable接口不一样，Callable接口提供了一个call（）方法作为线程执行体，call()方法比run()方法功能要强大。
        》call()方法可以有返回值
        》call()方法可以声明抛出异常
        
**2、sleep() 、join（）、yield（）有什么区别：**

    1.sleep()方法
        在指定时间内让当前正在执行的线程暂停执行，但不会释放“锁标志”。不推荐使用。
        sleep()使当前线程进入阻塞状态，在指定时间内不会执行。
    2.wait()方法
        在其他线程调用对象的notify或notifyAll方法前，导致当前线程等待。
        线程会释放掉它所占有的“锁标志”，从而使别的线程有机会抢占该锁。
        当前线程必须拥有当前对象锁。如果当前线程不是此锁的拥有者，会抛出IllegalMonitorStateException异常。
        唤醒当前对象锁的等待线程使用notify或notifyAll方法，也必须拥有相同的对象锁，
        否则也会抛出IllegalMonitorStateException异常。
        waite()和notify()必须在synchronized函数或synchronized　block中进行调用。
        如果在non-synchronized函数或non-synchronized　block中进行调用，虽然能编译通过，
        但在运行时会发生IllegalMonitorStateException的异常。
    3.yield方法
        暂停当前正在执行的线程对象。
        yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
        yield()只能使同优先级或更高优先级的线程有执行的机会。 
    4.join方法
        等待该线程终止。
        
**3、说说 CountDownLatch 原理**
    
**4、说说 CyclicBarrier 原理**

**5、说说 Semaphore 原理**

**6、说说 Exchanger 原理**

**7、说说 CountDownLatch 与 CyclicBarrier 区别**

**8、ThreadLocal 原理分析**

**9、讲讲线程池的实现原理**

**10、线程池的几种方式**

**11、线程的生命周期**