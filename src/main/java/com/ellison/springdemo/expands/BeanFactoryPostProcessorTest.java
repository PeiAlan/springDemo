package com.ellison.springdemo.expands;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * org.springframework.beans.factory.config.BeanFactoryPostProcessor
 *
 * 这个接口是beanFactory的扩展接口，调用时机在spring在读取beanDefinition信息之后，实例化bean之前。
 *
 * 在这个时机，用户可以通过实现这个扩展接口来自行处理一些东西，比如修改已经注册的beanDefinition的元信息。
 *
 * 扩展方式为：
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 10:59
 **/
//@Service
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {
    /**
     * ------spring在读取 beanDefinition 信息之后
     *
     *  调用此方法 postProcessBeanFactory()。
     *  目的：通过实现这个扩展接口来自行处理一些东西
     *      例如通过 getBeanDefinition() 获取已注册的 beanDefinition，并对其进行修改
     *
     * -----实例化bean之前
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("===========执行 postProcessBeanFactory() 扩展接口 ");
    }
}
