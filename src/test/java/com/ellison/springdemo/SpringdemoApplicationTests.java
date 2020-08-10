package com.ellison.springdemo;

import com.ellison.springdemo.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private CacheService cacheService;

    @Test
    public void test333() {
        Cache cache = cacheManager.getCache("redisCache");
        cache.put("cacheName","redisCache");
        Cache.ValueWrapper cacheName = cache.get("cacheName");
        System.out.println(cacheName.get());
    }

}
