package com.ellison.springdemo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 告知spring容器这是个 **全局捕获异常类**
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public String exception1(ArrayIndexOutOfBoundsException e){
        System.out.println("数组越界："+e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exception2(Exception e){
        System.out.println(e.getMessage());
        return "error";
    }

    @ExceptionHandler(ArithmeticException.class)
    public String exception3(ArithmeticException e){
        System.out.println(e.getMessage());
        return "error";
    }
}