package com.ellison.springdemo.springStudy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 动态代理
 * @author ellisonpei
 *
 */
public class PeopleProxy implements InvocationHandler {

    People people;
    public PeopleProxy(People people){
        this.people = people;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(people, args);
        after();
        return invoke;
    }

    public void before(){
        System.out.println("===============前置通知");
    }

    public void after(){
        System.out.println("===============后置通知");
    }
}
