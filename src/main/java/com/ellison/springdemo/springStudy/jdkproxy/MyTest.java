package com.ellison.springdemo.springStudy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * 测试
 * @author ellisonpei
 */
public class MyTest {
    public static void main(String[] args) {
        People people = (People) Proxy.newProxyInstance(MyTest.class.getClassLoader(),
                new Class<?>[]{People.class},
                new PeopleProxy(new Ellison()));
        people.eat("Ellison");

    }
}
