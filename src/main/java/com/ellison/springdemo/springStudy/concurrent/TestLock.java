package com.ellison.springdemo.springStudy.concurrent;

import com.ellison.springdemo.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/28 15:17
 **/
@Slf4j(topic = "ellison")
public class TestLock {
    private static final User l = new User();
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) throws InterruptedException {

        t1 = new Thread(() -> {
            testLock();
        });
        t2 = new Thread(() -> {
            testLock();
        });
        t3 = new Thread(() -> {
            testLock();
        });

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t1.join();
        t2.start();
//        t2.join();
        t3.start();

    }

    public static void testLock(){

        synchronized (l){

            log.debug(ClassLayout.parseInstance(l).toPrintable());
        }
    }
}
