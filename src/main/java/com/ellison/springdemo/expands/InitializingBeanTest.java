package com.ellison.springdemo.expands;

import org.springframework.beans.factory.InitializingBean;

/**
 * org.springframework.beans.factory.InitializingBean
 *
 * 这个类，顾名思义，也是用来初始化bean的。
 * InitializingBean接口为bean提供了初始化方法的方式，
 * 它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
 * 这个扩展点的触发时机在postProcessAfterInitialization之前。
 *
 * 使用场景：
 *      用户实现此接口，来进行系统启动的时候一些业务指标的初始化工作。
 *
 * 扩展方式为：
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 15:43
 **/
//@Service
public class InitializingBeanTest implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("===========[InitializingBeanTest] afterPropertiesSet()");
    }
}
