package com.ellison.springdemo.common.service;

import com.ellison.springdemo.common.entity.ConsultConfigArea;

import java.util.List;

public interface CacheService {

    String queryData(String id);

    List<ConsultConfigArea> queryAreaBystate(String state);

    String putCache(String id);

    String getCache(String id);

    String mapCache(String id);
}
