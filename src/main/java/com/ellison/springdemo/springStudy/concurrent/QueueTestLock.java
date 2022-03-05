package com.ellison.springdemo.springStudy.concurrent;

import sun.misc.Unsafe;

import java.util.Queue;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/28 17:49
 **/
public class QueueTestLock {

    private static volatile int status = 0;
    private static Queue parkQueue;//集合 数组 list
    private static final sun.misc.Unsafe U;
    static {
        U = Unsafe.getUnsafe();
    }

    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
           method();
        });
        t2 = new Thread(() -> {
            method();
        });
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }

    private static void method(){
        if (status == 0){
            System.out.println("执行线程：" + Thread.currentThread().getName());
        } else {
            park();
        }

    }

    private static void lock() {
        while (!compareAndSet(0, 1)) {
            park();
        }
    //lock 10分钟
        unlock();
    }

    private static void unlock() {
        lock_notify();
    }

    private static void park() {
        status = 1;
//将当期线程加入到等待队列
        parkQueue.add(Thread.currentThread());
//将当期线程释放cpu 阻塞
        releaseCpu();
    }

    private static void releaseCpu() {
        status = 0 ;
    }

    private static void lock_notify() {
//得到要唤醒的线程头部线程
        Thread t = (Thread) parkQueue.peek();
//唤醒等待线程
        U.unpark(t);
    }

    private static boolean compareAndSet(int export, int newValue){
            return U.compareAndSwapInt(null,0, export, newValue);
    }
}
