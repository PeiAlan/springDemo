package com.ellison.springdemo.springStudy.concurrent.cas;

import com.ellison.springdemo.springStudy.concurrent.entity.L;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>cas 原理学习</p>
 *
 * @author Ellison Pei
 * @date 2020/8/31 12:35
 **/
@Slf4j(topic = "ellison")
public class CasTest {



    static L l = new L();
    public static void main(String[] args) throws InterruptedException {
//        log.debug(ClassLayout.parseInstance(l).toPrintable());

        casTest(new AccountCas(100));

    }

    static void casTest(Account account) throws InterruptedException {
        //定义500个线程，每个线程针对account取20
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            threadList.add(new Thread(() -> {
                account.acquire(20);
            }));
        }

        //启动
        for (Thread thread : threadList){
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }



        log.debug(account.query().toString());

    }
}
