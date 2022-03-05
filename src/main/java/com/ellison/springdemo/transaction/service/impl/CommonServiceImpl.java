package com.ellison.springdemo.transaction.service.impl;

import com.ellison.springdemo.common.entity.ConsultConfigArea;
import com.ellison.springdemo.common.entity.ZgGoods;
import com.ellison.springdemo.common.entity.ZgTicket;
import com.ellison.springdemo.common.mapper.CommonMapper;
import com.ellison.springdemo.transaction.service.AreaService;
import com.ellison.springdemo.transaction.service.CommonService;
import com.ellison.springdemo.transaction.service.GoodsService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AreaService areaService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    CommonServiceImpl commonService;


    //开启了事务
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    @Override
    public void transation(ConsultConfigArea area, ZgGoods zgGoods) {
        try {
            //事务1  传播属性为REQUIRED
//            areaService.addArea(area);
            commonService.add(area);  //建立一个事务a
            Thread.sleep(20000);
            //事务2  传播属性为REQUIRES_NEW
            area.setAreaName(zgGoods.getGoodCode());
//            areaService.updateArea(area); //建立一个事务b
//            commonService.updateArea(area);
            goodsService.addGoods(zgGoods);
            System.out.println("111");
        }catch (Exception e){

        }
    }
    //提交事务
    // required   required_new


//    public void test222(){
//        //transation 方法  nested
//        try {
//            //addArea 方法 nested
//            //开启事务 ()
//            try {
//                add();
//
//            }catch (Exception e){
//                //事务回滚?
//                throw e;
//            }
//
//            //addGoods 方法 nested
//            //开启事务 ()
//            try {
//                addGoods();
                    //   有异常发生
//            }catch (Exception e){
//                //事务回滚?
//                throw e;
//            }
//
//        }catch (Exception e){
//
//        }
//    }
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public int add(ConsultConfigArea area){
        int i = commonMapper.addArea(area);
        return i;
    }

    //注解放在类上，然后类中方法上也加了注解，为什么没有生效
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateArea(ConsultConfigArea area) {
        int i = commonMapper.updateArea(area);
        if (i <= 0){
            System.out.println("数据插入失败");
        }
        return i;
    }


    @Transactional
    @Override
    public int getTicket() {

        //1、获取锁
        List<ZgTicket> zgTickets = commonMapper.queryTicketById("12306");
        Map lockmap = new HashMap();
        lockmap.put("ticketId", "12306");
        lockmap.put("version", zgTickets.get(0).getVersion());
        int i = commonMapper.updateLock(lockmap);

        if (i > 0) {
            //抢票
            ZgTicket zgTicket = zgTickets.get(0);
            zgTicket.setTicketCount(2);
            int i1 = commonMapper.updateTicket(zgTicket);
        } else {
            //继续抢
            ((CommonService) AopContext.currentProxy()).getTicket();
        }

        return 0;
    }

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    public void xxx() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(0);
        TransactionStatus transaction = platformTransactionManager.getTransaction(defaultTransactionDefinition);

        try {
            System.out.println("业务代码");
        }catch (Exception e) {
            platformTransactionManager.rollback(transaction);
        }

        platformTransactionManager.commit(transaction);
    }


    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public int getTicketModeOne() {
        Integer execute = transactionTemplate.execute(status -> {
            //1、获取锁
            List<ZgTicket> zgTickets = commonMapper.queryTicketById("12306");
            Map lockmap = new HashMap();
            lockmap.put("ticketId", "12306");
            lockmap.put("version", zgTickets.get(0).getVersion());
            int i = commonMapper.updateLock(lockmap);

            if (i > 0) {
                //抢票
                ZgTicket zgTicket = zgTickets.get(0);
                zgTicket.setTicketCount(2);
                int i1 = commonMapper.updateTicket(zgTicket);
            }
            return i;
        });
        if (execute == 0) {
            //继续抢
            getTicketModeOne();
        }
        return 0;
    }
}
