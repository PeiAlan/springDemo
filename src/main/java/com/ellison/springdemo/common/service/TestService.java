package com.ellison.springdemo.common.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * 测试Service
 *
 * @Author Ellison Pei
 * @Date 2021/8/30 14:02
 **/
@Service
public class TestService {

//    @Autowired
//    private RedisUtil redisUtil;

    private final Object o = new Object();

    private final String PREFIX = "test_";

    private final Integer EXPIRE_TIME = 30;

    private final Integer count = 1;
    private final CountDownLatch countDownLatch = new CountDownLatch(count);

//    public void testLock() {
////        ExecutorService executorService1 = Executors.newFixedThreadPool(1, CustomThreadFactory.create("111"));
//        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
//                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), CustomThreadFactory.create("testLock"));
//        try {
//            executorService.submit(() -> {
//                String value = (String) redisUtil.get(PREFIX + "lock");
//                if (EllisonStringUtil.isBlank(value)) {
//                    value = Thread.currentThread().getName() + "从Redis取接口正常返回结果";
//                    if (redisUtil.set(PREFIX + "lock", value, 3600)) {
//                        sleep(5);
//                        System.out.println(Thread.currentThread().getName() + "触发了接口！并已存入redis缓存");
//                    }
//                }
//                System.out.println(value);
//                countDownLatch.countDown();
//            });
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            executorService.shutdown();
//        }
//
//    }
//
//    public void testLock2() {
//        System.out.println("其他业务操作。。。。。。");
//        synchronized (o) {
//            String value = (String) redisUtil.get(PREFIX + "lock");
//            if (EllisonStringUtil.isBlank(value)) {
//                String result = Thread.currentThread().getName() + "接口正常返回结果";
//                if (redisUtil.set(PREFIX + "lock", result, 30)) {
//                    sleep(5);
//                    System.out.println(Thread.currentThread().getName() + "触发了接口！并已存入redis缓存");
//                }
//            } else {
//                System.out.println(value);
//            }
//        }
//    }

    private void sleep(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
