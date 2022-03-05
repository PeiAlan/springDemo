package com.ellison.springdemo.redis.controller;

import com.ellison.springdemo.redis.service.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试学习Redis操作
 * @author Ellison Pei
 * @date 2021/9/1 14:44
 **/
@Slf4j
@RestController
@RequestMapping("/redis/")
public class RedisTestController {

    private final RedisTestService service;

    @Autowired
    public RedisTestController(RedisTestService redisTestService) {
        this.service = redisTestService;
    }
    @GetMapping("/test1")
    public String test1(){
        return "success";
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("redisUtil")
    public String testRedisTemplate(){
        return service.testRedisTemplate();
    }

    @GetMapping("jedisUtil")
    public String testJedis(){
        return service.testJedis();
    }

    @GetMapping("bloom/insert")
    public void testBloomInsert(){
        service.testInsert();
    }

    @GetMapping("bloom/mayExist")
    public void testMayExist(){
        service.testMayExist();
    }
}
