package com.ellison.springdemo.expands;

import org.springframework.beans.factory.DisposableBean;

/**
 * org.springframework.beans.factory.DisposableBean
 *
 * 这个扩展点也只有一个方法：destroy()，其触发时机为当此对象销毁时，会自动执行这个方法。
 * 比如说运行applicationContext.registerShutdownHook时，就会触发这个方法。
 *
 * 扩展方式为：
 *
 * @Author Ellison Pei
 * @Date 2020/10/19 14:27
 **/
public class DisposableBeanTest implements DisposableBean {

    /**
     * 自定义销毁对象的方法
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {

    }
}
