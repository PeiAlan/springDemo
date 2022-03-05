package com.ellison.springdemo.transaction.service;

import com.ellison.springdemo.common.entity.ConsultConfigArea;

import java.util.List;
import java.util.Map;

public interface AreaService {

    List<ConsultConfigArea> queryAreaFromDB(Map param);

    String queryAreaFromRedisOne(Map param);

    String queryAreaFromRedisTow(Map param);

    int addArea(ConsultConfigArea area);

    int updateArea(ConsultConfigArea area);


}
