package com.ellison.springdemo.expands;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;

/**
 * org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor
 *
 * 该扩展接口有3个触发点方法：
 *
 * 1、predictBeanType：
 *      该触发点发生在 postProcessBeforeInstantiation 之前(一般不太需要扩展这个点)，
 *      这个方法用于预测Bean的类型，返回第一个预测成功的Class类型，如果不能预测返回null；
 *      当你调用 BeanFactory.getType(name) 时当通过bean的名字无法得到bean类型信息时就调用该回调方法来决定类型信息。
 * 2、determineCandidateConstructors：
 *      该触发点发生在 postProcessBeforeInstantiation 之后，用于确定该bean的构造函数之用，
 *      返回的是该bean的所有构造函数列表。
 *      用户可以扩展这个点，来自定义选择相应的构造器来实例化这个bean。
 * 3、getEarlyBeanReference：
 *      该触发点发生在 postProcessAfterInstantiation 之后，当有循环依赖的场景，
 *      当bean实例化好之后，为了防止有循环依赖，会提前暴露回调方法，用于bean实例化的后置处理。
 *      这个方法就是在提前暴露的回调方法中触发。
 *
 * 扩展方式为：
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 14:19
 **/
//@Service
public class SmartInstantiationAwareBeanPostProcessorTest implements SmartInstantiationAwareBeanPostProcessor {

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[SmartInstantiationAwareBeanPostProcessorTest] predictBeanType "+ beanName);
        return beanClass;
    }

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[SmartInstantiationAwareBeanPostProcessorTest] determineCandidateConstructors "+ beanName);
        return new Constructor[0];
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        System.out.println("[SmartInstantiationAwareBeanPostProcessorTest] getEarlyBeanReference "+ beanName);
        return bean;
//        return null;
    }
}
