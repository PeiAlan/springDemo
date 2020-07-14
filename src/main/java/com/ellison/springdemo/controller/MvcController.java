package com.ellison.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * MVC 功能测试接口
 *
 */
@Controller
@RequestMapping("/mvc")
public class MvcController {


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


}
