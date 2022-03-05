package com.ellison.springdemo.springStudy.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/31 13:01
 **/
public class AccountCas implements Account {
    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }
    private AtomicInteger balance;

    @Override
    public Integer query() {
        return this.balance.get();
    }

    @Override
    public void acquire(Integer i) {
        while (true) {
            int prev = balance.get();
            int next = prev - i;
            if (balance.compareAndSet(prev, next)){
                break;
            }
        }
    }
}
