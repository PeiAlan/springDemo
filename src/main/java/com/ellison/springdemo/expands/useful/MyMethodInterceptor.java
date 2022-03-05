package com.ellison.springdemo.expands.useful;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 自定义方法拦截器
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 11:47
 **/
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("目标方法前:" + method+"\n");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("目标方法后:" + method+"\n");
        return object;
    }
}
