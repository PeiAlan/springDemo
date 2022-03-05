package com.ellison.springdemo.springStudy.jdkproxy;

/**
 * @author ellisonpei
 */
public class Ellison implements People {
    @Override
    public String eat(String name) {
        System.out.println("Ellison===========执行eat（）方法");
        return name;
    }
}
