package com.ellison.springdemo.springStudy.concurrent.cas;

/**
 * <p></p>
 *
 * @author Ellison Pei
 * @Date 2020/8/31 13:00
 **/
public interface Account {

    Integer query();
    void acquire(Integer i);
}
