package com.ellison.springdemo.expands;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Service;

/**
 * GenericApplicationContext 属性设置
 *
 * @author Ellison_Pei
 * @date 2022/3/4 10:40
 * @since 1.0
 **/
public class ApplicationContextInitializerTest2 implements ApplicationContextInitializer<GenericApplicationContext> {
    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        // 设置禁止循环依赖
        applicationContext.setAllowCircularReferences(false);
    }
}
