package com.ellison.springdemo.test.transaction.service;

import com.ellison.springdemo.entity.ZgGoods;

import java.util.List;

public interface GoodsService {

    void addGoods(ZgGoods zgGoods);

    List<ZgGoods> queryAll();
}
