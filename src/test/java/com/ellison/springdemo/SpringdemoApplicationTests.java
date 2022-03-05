package com.ellison.springdemo;

import com.ellison.springdemo.common.service.CacheService;
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
//    @Test
//    public void test2(){
//        //Chrome驱动下载地址（MacOS）https://chromedriver.storage.googleapis.com/88.0.4324.96/chromedriver_mac64.zip
//        //设置驱动环境变量 '/Users/ellisonpei/Desktop/apache/chromedriver_mac64/chromedriver'是我所下载的Chrome驱动的地址
//        System.setProperty("webdriver.chrome.driver","/Users/ellisonpei/Desktop/apache/chromedriver_mac64/chromedriver");
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.get("https://www.gitee.com");
//    }

}
