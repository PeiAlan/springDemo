package com.ellison.springdemo.redis.service;

/**
 * Redis 操作类
 *
 * @author Ellison Pei
 * @date 2021/9/1 14:46
 **/
public interface RedisTestService {
    /***
     * 测试 redisTemplate
     * @return void
     * @throws Exception
     * @author Ellison.Pei
     * @date 2021/9/1 17:43
     * @version 1.0
     */
    String testRedisTemplate();

    /***
     *  测试 Jedis
     * @return java.lang.String
     * @throws Exception
     * @author Ellison.Pei
     * @date 2021/9/2 11:01
     * @version 1.0
     */
    String testJedis();

    void testInsert();

    void testMayExist();


}
