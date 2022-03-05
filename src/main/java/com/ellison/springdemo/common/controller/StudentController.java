package com.ellison.springdemo.common.controller;

import com.ellison.springdemo.common.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "msg", required = true) String msg){
        return studentService.method1(msg);
    }
    @RequestMapping("/hello2")
    public String hello2(@RequestParam(value = "msg", required = true) String msg, @RequestParam(value = "s", required = true) String s){
        return studentService.method2(msg,s);
    }
}
