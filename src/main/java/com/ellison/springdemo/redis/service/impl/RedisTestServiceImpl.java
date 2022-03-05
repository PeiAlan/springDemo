package com.ellison.springdemo.redis.service.impl;

import com.ellison.springdemo.config.thread.CustomThreadFactory;
import com.ellison.springdemo.redis.filter.RedisBloomFilter;
import com.ellison.springdemo.redis.service.RedisTestService;
import com.ellison.springdemo.utils.JedisUtil;
import com.ellison.springdemo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试redis实现类<pr>
 *
 * @author Ellison Pei
 * @date 2021/9/1 16:25
 **/
@Slf4j
@Service
public class RedisTestServiceImpl implements RedisTestService {

    /**
     * RedisTemplate来实现Redis的访问操作
     */
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private RedisBloomFilter redisBloomFilter;

    private static final int DAY_SEC = 60 * 60 * 24;


    @Override
    public String testRedisTemplate() {
        System.out.println(redisUtil);
        String s = redisUtil.toString();
        return s;
    }

    @Override
    public String testJedis() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1000,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), CustomThreadFactory.create("redis-springStudy"));
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                log.info(Thread.currentThread().getName() + "++++++++++");
            });
        }
        StringBuilder result = new StringBuilder();
        String setResult = jedisUtil.setex("testJedis", "ellisonPei", 30);
        String getResult = jedisUtil.get("testJedis");
        result.append("setResult ： ").append(setResult)
                .append(", ")
                .append("getResult ： ").append(getResult);
        executor.shutdown();
        try {
            executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 不管任务结没结束， 直接关闭    kill -9
//        executor.shutdownNow();
        return result.toString();
    }

    @Override
    public void testInsert() {
        try {
            System.out.println(redisBloomFilter);
            redisBloomFilter.insert("topic_read:8839540:20210810", "76930242", DAY_SEC);
            redisBloomFilter.insert("topic_read:8839540:20210810", "76930243", DAY_SEC);
            redisBloomFilter.insert("topic_read:8839540:20210810", "76930244", DAY_SEC);
            redisBloomFilter.insert("topic_read:8839540:20210810", "76930245", DAY_SEC);
            redisBloomFilter.insert("topic_read:8839540:20210810", "76930246", DAY_SEC);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void testMayExist(){
        try {
            System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20210810", "76930242"));
            System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20210810", "76930244"));
            System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20210810", "76930246"));
            System.out.println(redisBloomFilter.mayExist("topic_read:8839540:20210810", "86930250"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
