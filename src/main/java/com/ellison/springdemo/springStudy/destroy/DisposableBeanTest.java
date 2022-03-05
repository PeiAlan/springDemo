package com.ellison.springdemo.springStudy.destroy;

import org.springframework.beans.factory.DisposableBean;

/**
 * Bean销毁是 做操作
 * 其他方式还有 使用注解 @PreDestroy
 *
 * @author Ellison_Pei
 * @date 2022/3/5 15:52
 * @since 1.0
 **/
//@Component
public class DisposableBeanTest implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("===============DisposableBeanTest:destroy()===========");
    }

    //@PreDestroy
    public void disposable(){
        System.out.println("===============DisposableBeanTest:disposable()===========");
    }
}
