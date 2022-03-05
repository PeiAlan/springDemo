package com.ellison.springdemo.transaction.service;

import com.ellison.springdemo.common.entity.ZgGoods;

import java.util.List;

public interface GoodsService {

    void addGoods(ZgGoods zgGoods);

    List<ZgGoods> queryAll();

    void handleGoods(ZgGoods zgGoods);
}
