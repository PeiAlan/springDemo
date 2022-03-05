package com.ellison.springdemo.common.controller;

import com.ellison.springdemo.common.service.CacheService;
import com.ellison.springdemo.common.entity.ConsultConfigArea;
import com.ellison.springdemo.transaction.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * MVC 功能测试接口
 *
 */
@Controller
@RequestMapping("/mvc")
public class MvcController {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private AreaService areaService;


    /**
     * 无参接口
     * @return string
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    /**
     * 带参数接口 ，使用注解@RequestParam
     * @param name
     * @return
     */
    @RequestMapping(value = "/testAdd", method = RequestMethod.POST)
    @ResponseBody
    public String test1(@RequestParam(value = "name", defaultValue = "") String name){
        return name;
    }

    /**
     * 带参数接口 ，使用注解@PathVariable
     * @param name
     * @return
     */
    @RequestMapping(value = "/testAdd/{name}/{age}", method = RequestMethod.POST)
    @ResponseBody
    public String test2(@PathVariable(value = "name", required = true) String name,
                        @PathVariable(value = "age", required = true) int age){
//        int a = 15 ;
//        a |= a >>> 1;
//        a |= a >>> 2;
//        a |= a >>> 4;
//        a |= a >>> 8;
//        a |= a >>> 16;
//        int n = (a < 0) ? 1 : (a >= (1 << 30)) ? (1 << 30) : a + 1;
        return "name: "+ name + "\nage: " + age;
    }

    /**
     * 跨域请求：
     * @param name
     * @return String
     */
    @CrossOrigin(value = "http://localhost:8080")
    @RequestMapping(value = "/cors/{name}", method = RequestMethod.POST)
    @ResponseBody
    public String cors(@PathVariable(value = "name", required = true)String name){
        return name;
    }

    /**
     * 缓存查询数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryData/{id}")
    @ResponseBody
    public String queryData(@PathVariable(value = "id", required = true)String id){
        return cacheService.queryData(id);
    }

    /**
     *  批量插入数据
     * @param number
     * @return
     */
    @RequestMapping(value = "/addData/{number}")
    @ResponseBody
    @Transactional
    public String addData(@PathVariable(value = "number", required = true) int number){
        boolean flag = true;
        for (int i = 0; i < number; i++) {
            ConsultConfigArea area = new ConsultConfigArea();
            area.setAreaCode("BJ" + i);
            area.setAreaName("BJ" + i);
            area.setState(String.valueOf(i));
            int iresult = areaService.addArea(area);
            if(iresult <= 0) {
                flag = false;
            }
        }
        if (!flag){
            return "error";
        }
        return "success";
    }

}
