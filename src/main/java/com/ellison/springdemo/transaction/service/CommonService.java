package com.ellison.springdemo.transaction.service;

import com.ellison.springdemo.common.entity.ConsultConfigArea;
import com.ellison.springdemo.common.entity.ZgGoods;

/**
 * 事务逻辑
 * @author ellisonpei
 */
public interface CommonService {

    void transation(ConsultConfigArea area, ZgGoods zgGoods);

    int getTicket();

    int getTicketModeOne();
}
