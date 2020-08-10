package com.ellison.springdemo.test.transaction.service;

import com.ellison.springdemo.entity.ConsultConfigArea;
import com.ellison.springdemo.entity.ZgGoods;

/**
 * 事务逻辑
 * @author ellisonpei
 */
public interface CommonService {

    void transation(ConsultConfigArea area, ZgGoods zgGoods);

    int getTicket();

    int getTicketModeOne();
}
