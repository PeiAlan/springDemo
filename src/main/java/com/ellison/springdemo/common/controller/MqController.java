package com.ellison.springdemo.common.controller;

import com.ellison.springdemo.common.service.mq.DefaultSender;
import com.ellison.springdemo.common.service.mq.HelloReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>测试Rabbit MQ 案例</p>
 *
 * @author Ellison Pei
 * @date 2020/8/8 18:21
 **/
@RestController
@RequestMapping("/rabbit")
public class MqController {

    @Autowired
    private DefaultSender defaultSender;
    @Autowired
    private HelloReceiver helloReceiver;

    @RequestMapping("/hello")
    public void hello(@RequestParam(value = "msg", defaultValue = "") String msg){
        defaultSender.send(msg);

    }

}
