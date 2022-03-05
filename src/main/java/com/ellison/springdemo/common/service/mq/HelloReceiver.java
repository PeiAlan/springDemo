package com.ellison.springdemo.common.service.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>监听Hello队列</p>
 *
 * @author Ellison Pei
 * @date 2020/8/8 18:34
 **/
@Component
@RabbitListener(queues = "sb.hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.err.println("HelloReceiver : " + hello);
    }
}
