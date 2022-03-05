package com.ellison.springdemo.springStudy.circular;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 循环依赖问题
 *
 * @author Ellison_Pei
 * @date 2022/3/4 13:38
 * @since 1.0
 **/
//@Data
//@Service
public class CircularRefA {

    //@Value("CircularRefA")
    //private String username;

    @Autowired
    private CircularRefB circularRefB;

    public CircularRefA(){
        System.out.println("====================CircularRefA()=========================");
    }
}
