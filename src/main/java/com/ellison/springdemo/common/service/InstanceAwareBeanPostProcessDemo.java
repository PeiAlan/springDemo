package com.ellison.springdemo.common.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

//@Service
public class InstanceAwareBeanPostProcessDemo implements InstantiationAwareBeanPostProcessor {

    /**
     * 直接返回false，导致所有依赖注入全部失效
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }
}
