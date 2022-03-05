package com.ellison.springdemo.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ellison.springdemo.common.mapper.CommonMapper;
import com.ellison.springdemo.common.entity.ConsultConfigArea;
import com.ellison.springdemo.common.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CommonMapper commonMapper;

    @Cacheable(cacheNames = "redisCache",key = "'ellison' + #id")
    @Override
    public String queryData(String id) {
        System.out.println("======CacheServiceImpl.queryData");
        List<ConsultConfigArea> areas = commonMapper.queryAreaById(id);
        return JSONObject.toJSONString(areas);
    }

    @Cacheable(cacheNames = "redisCache",key = "'goodsByState:' + #state")
    @Override
    public List<ConsultConfigArea> queryAreaBystate(String state) {
        System.out.println("======CacheServiceImpl.queryAreaBystate");
        List<ConsultConfigArea> areas = commonMapper.queryAreaBystate(state);
        return areas;
    }

    @CachePut(cacheNames = "redisCache",key = "'ellison' + #id")
    @Override
    public String putCache(String id) {
        System.out.println("======CacheServiceImpl.putCache");
        List<ConsultConfigArea> areas = commonMapper.queryAreaById(id);
        return JSONObject.toJSONString(areas);
    }

    @Cacheable(cacheNames = "redisCache",key = "'ellison' + #id")
    @Override
    public String getCache(String id) {
        return null;
    }

    @Cacheable(cacheNames = "mapCache",key = "'ellison' + #id")
    @Override
    public String mapCache(String id) {
        System.out.println("=========CacheServiceImpl.mapCache");
        return "数据存储在map中";
    }
}
