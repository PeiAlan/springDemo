package com.ellison.springdemo.test.transaction.service.impl;

import com.ellison.springdemo.mapper.CommonMapper;
import com.ellison.springdemo.entity.ZgGoods;
import com.ellison.springdemo.test.transaction.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private CommonMapper commonMapper;

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void addGoods(ZgGoods zgGoods) {
        int i = commonMapper.addGood(zgGoods);
        if(true) {
            throw new RuntimeException("yic");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ZgGoods> queryAll() {
        return commonMapper.queryAll();
    }
}
