package com.ellison.springdemo.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 告知spring容器这是个 **全局捕获异常类**
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public String exception1(ArrayIndexOutOfBoundsException e) {
        logger.error("数组越界：" + e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exception2(Exception e) {
        logger.error(e.getMessage());
        return "error";
    }

    @ExceptionHandler(ArithmeticException.class)
    public String exception3(ArithmeticException e) {
        logger.error(e.getMessage());
        return "error";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public String exception4(MissingServletRequestParameterException e) {
        logger.error("缺少参数：请输入参数" + e.getParameterName() + "的值");
        return "缺少参数：请检查参数" + e.getParameterName() + "的值";
    }
}