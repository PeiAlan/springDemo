package com.ellison.springdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ellison.springdemo.entity.*;
import com.ellison.springdemo.entity.test.Response;
import com.ellison.springdemo.entity.test.ResponseParam;
import com.ellison.springdemo.test.transaction.service.AreaService;
import com.ellison.springdemo.test.transaction.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;
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


    /**
     *  事务测试接口
     * @param areas
     * @param goods
     * @return
     */
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

    /**
     * 取物料凭证号
     */
    @PostMapping("/getNo")
    public void getNumber(@RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        List<ResponseParam> responseParamList = jsonObject.getJSONArray("body").toJavaList(ResponseParam.class);
        try (PrintStream printStream = new PrintStream("number.txt")) {
            PrintStream out = System.out;
            System.setOut(printStream);
            System.out.println("============打印开始");
            for (ResponseParam responseParam : responseParamList) {
                Response response = JSONObject.parseObject(responseParam.getResponse_param(), Response.class);
                String evmblnr = response.getResponseData().getBody().getEVMBLNR();
                System.out.println(evmblnr);
            }
            System.out.println("============打印结束");
            System.setOut(out);
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
