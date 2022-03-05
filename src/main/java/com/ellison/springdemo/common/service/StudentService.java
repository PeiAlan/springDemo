package com.ellison.springdemo.common.service;

import org.springframework.stereotype.Service;

/**
 * @author ellisonpei
 */
@Service
public class StudentService {

    public String method1(String msg){
        System.out.println("执行了一些业务处理。。。。。。。。。");
        return msg;

    }

    public String method2(String msg, String s){
        System.out.println(msg + " : " +s);
        return msg;
    }
}
