package com.ellison.springdemo.controller;

import com.ellison.springdemo.pojo.ConsultConfigArea;
import com.ellison.springdemo.pojo.ZgGoods;
import com.ellison.springdemo.test.transaction.service.AreaService;
import com.ellison.springdemo.test.transaction.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AreaService areaService;
    @Autowired
    private CommonService commonService;


    @RequestMapping("/query1")
    public List<ConsultConfigArea> queryAreaFromDB(){
        Map param = new HashMap();
        param.put("areaCode","HB1");
        return areaService.queryAreaFromDB(param);
    }

    @RequestMapping("/add/{areas}/{goods}")
    public String add(@PathVariable(value = "areas", required = true)String areas,
                      @PathVariable(value = "goods", required = true)String goods){
        ConsultConfigArea area = new ConsultConfigArea();
        area.setAreaCode(areas);
        area.setAreaName(areas);
        area.setState("1");

        ZgGoods zgGoods = new ZgGoods();
        zgGoods.setGoodCode(goods);
        zgGoods.setGoodName(goods);
        zgGoods.setCount(100);

        commonService.transation(area,zgGoods);
        return "1111";
    }

}
