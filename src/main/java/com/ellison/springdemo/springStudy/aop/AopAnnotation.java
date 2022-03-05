package com.ellison.springdemo.springStudy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ellisonpei
 */

@Component
@Aspect
public class AopAnnotation {

    @Pointcut("execution(public * com.ellison.springdemo.common.service.*.*(..))")
    public void serviceAdvice(){}

    @Pointcut("execution(public * com.ellison.springdemo.common.service.*.method1(..))")
    public void method1(){}

    @Pointcut("execution(public * com.ellison.springdemo.common.service.StudentService.method2(..))")
    public void method2(){}

    @Before("serviceAdvice()")
    public void advice1(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
//        System.out.println("前置增强");
    }

    @Around("method1()")
    public String advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("前置通知");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("后置通知");
        return proceed.toString();

    }

    @After(value = "method2()")
    public void advice2(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        List<String> argList = new ArrayList<String>();
        for (Object arg : args) {
            argList.add(arg.toString());
        }
        System.out.println("后置增强处理" + argList);
    }
}
