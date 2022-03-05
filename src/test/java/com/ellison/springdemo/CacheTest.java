package com.ellison.springdemo;

import com.ellison.springdemo.common.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest
public class CacheTest {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private CacheService cacheService;

    @Test
    public void test1() {
        Cache cache = cacheManager.getCache("redisCache");
        cache.put("cacheName","redisCache");
        Cache.ValueWrapper cacheName = cache.get("cacheName");
        System.out.println(cacheName.get());
    }

    @Test
    public void test2() {
        cacheService.queryData("XJ14");
    }

    @Test
    public void test3() {
        cacheService.putCache("XJ14");

        cacheService.getCache("XJ14");
    }

    @Test
    public void test4() {
        System.out.println(cacheService.mapCache("123456"));

        System.out.println(cacheService.mapCache("123456"));
    }
}
