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
public class CircularRefB {
    @Autowired
    private CircularRefA circularRefA;

    public CircularRefB(){
        System.out.println("====================CircularRefB()=========================");
    }

    /**
     * 这种依赖写法直接报错  在 beforeSingletonCreation方法直接throw
     * @param circularRefA
     */
    @Autowired
    public CircularRefB(CircularRefA circularRefA){
        System.out.println("====================CircularRefB()=========================");
    }
}
