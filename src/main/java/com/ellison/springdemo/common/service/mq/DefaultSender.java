package com.ellison.springdemo.common.service.mq;

import com.ellison.springdemo.utils.RmConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>默认发送者</p>
 *
 * @author Ellison Pei
 * @date 2020/8/8 18:32
 **/
@Component
public class DefaultSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        String sendMsg = msg +"---"+ System.currentTimeMillis();;
        System.out.println("Sender : " + sendMsg);
        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_HELLO, sendMsg);
        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_USER, sendMsg);
    }
}
