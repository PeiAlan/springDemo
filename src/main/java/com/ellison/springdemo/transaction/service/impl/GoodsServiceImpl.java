package com.ellison.springdemo.transaction.service.impl;

import com.ellison.springdemo.common.entity.ZgGoods;
import com.ellison.springdemo.common.mapper.CommonMapper;
import com.ellison.springdemo.transaction.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品实现类
 * @author Ellison Pei
 */
@Slf4j
@Service
// @Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private CommonMapper commonMapper;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    @Override
    public void addGoods(ZgGoods zgGoods) {
        int i = commonMapper.addGood(zgGoods);
        if(true) {
            log.error("addGoods方法抛出异常");
            throw new RuntimeException("yic");
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<ZgGoods> queryAll() {
        return commonMapper.queryAll();
    }

    @Override
    public void handleGoods(ZgGoods zgGoods) {
        // 先修改goods，再查询goods
        // 测试同一事物中，先修改数据，未提交情况下去数据库中查值
        // 结论：可以查到，但仅限于当前事务，能查到未提交事务的数据。但是仅限于当前事务（提交数据的事务）内

        zgGoods.setCount(666666);
        commonMapper.addGood(zgGoods);
        List<ZgGoods> zgGoods1 = commonMapper.queryGoodsByGoodCode(zgGoods.getGoodCode());
        zgGoods1.get(0);
        System.out.println("============");

    }
}
