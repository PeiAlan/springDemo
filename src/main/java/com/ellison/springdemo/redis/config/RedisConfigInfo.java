package com.ellison.springdemo.redis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis 配置信息
 *
 * @author Ellison_Pei
 * @date 2021/9/2 16:11
 * @since 1.0
 **/
@Component
@Data
public class RedisConfigInfo {
    @Value("${spring.redis.host}")
    private String ip;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String auth;

    @Value("${spring.redis.maxIdle}")
    private Integer maxIdle;
    @Value("${spring.redis.maxWait}")
    private Long maxWaitMillis;
    @Value("${spring.redis.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.redis.testOnCreate}")
    private Boolean testOnCreate;
}
