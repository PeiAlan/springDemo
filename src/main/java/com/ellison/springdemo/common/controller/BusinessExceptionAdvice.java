package com.ellison.springdemo.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>TODO</p>
 *
 * @Author Ellison Pei
 * @Date 2021/1/6 下午5:27
 **/
@ControllerAdvice
public class BusinessExceptionAdvice extends GlobalExceptionAdvice {

    public static Logger logger = LoggerFactory.getLogger(BusinessExceptionAdvice.class);

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String exception1(NullPointerException e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        StringBuffer errorBuffer = new StringBuffer();
        errorBuffer.append(stackTraceElement.getClassName() + "中，")
                .append(stackTraceElement.getMethodName() + "方法")
                .append("第" + stackTraceElement.getLineNumber() + "行")
                .append("出错：" + e.getMessage());
        logger.error(errorBuffer.toString());
        e.printStackTrace();
        return errorBuffer.toString();
    }
}
