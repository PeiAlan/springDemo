package com.ellison.springdemo.transaction.service.impl;

import com.ellison.springdemo.common.mapper.CommonMapper;
import com.ellison.springdemo.common.entity.ConsultConfigArea;
import com.ellison.springdemo.transaction.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AreaServiceImpl implements AreaService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonMapper commonMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = RuntimeException.class)
    @Override
    public List<ConsultConfigArea> queryAreaFromDB(Map param) {
        logger.info("================从mysql里面查询数据 事务1========================");
        List<ConsultConfigArea> areas = commonMapper.queryAreaByAreaCode(param);
        return areas;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public String queryAreaFromRedisOne(Map param) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("================从mysql里面查询数据 事务2========================");
        return "OK";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String queryAreaFromRedisTow(Map param) {
        return "OK";
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public int addArea(ConsultConfigArea area) {
        int i = commonMapper.addArea(area);
        if (i <= 0){
            logger.info("数据插入失败");
        }
//        if(true) {
//            throw new RuntimeException("yic");
//        }
        return i;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateArea(ConsultConfigArea area) {
        int i = commonMapper.updateArea(area);
        if (i <= 0){
            logger.info("数据插入失败");
        }
        return i;
    }
}
