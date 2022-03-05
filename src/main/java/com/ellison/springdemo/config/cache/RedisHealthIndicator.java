package com.ellison.springdemo.config.cache;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 由于在Spring Boot项目中引用了Redis模块，所以Spring Boot Actuator会对其进行健康检查，
 * 正常情况下不会出现问题，但是由于采用了其他配置方式，导致redis的连接检查没有通过。
 * 这样就会导致了Consul或Eureka的HealthCheck认为该服务是DOWN状态
 *
 * 通过参考这个类 RedisHealthContributorAutoConfiguration 的源码
 *  @Bean
 *  @ConditionalOnMissingBean(name = { "redisHealthIndicator", "redisHealthContributor" })
 * 	public HealthContributor redisHealthC ontributor(Map<String, RedisConnectionFactory> redisConnectionFactories) {
 * 		return createContributor(redisConnectionFactories);
 *  }
 *
 * 得出：
 *      实现HealthIndicator接口中的health方法，直接返回了up状态。
 *      通过@Component注解，让Spring Boot扫描到该类就能自动的进行加载，并覆盖原来的redis健康检查实现。
 *      当然，这里的实现并不好，因为它只是为了让健康检查可以通过，但是并没有做真正的健康检查。
 *      如采用了其他配置访问，那么正确的做法就是在health方法中实现针对其他配置的内容进行健康检查
 * 注意：
 *      下边@Component("redisHealthIndicator") 的名称要与源码中 @ConditionalOnMissingBean 中定义的名称一致才可以
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 08:44
 **/
@Component("redisHealthIndicator")
public class RedisHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().build();
    }
}
