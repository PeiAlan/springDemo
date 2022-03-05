package com.ellison.springdemo.expands;

import org.springframework.beans.factory.BeanNameAware;

/**
 * org.springframework.beans.factory.BeanNameAware
 *
 * 可以看到，这个类也是Aware扩展的一种，触发点在bean的初始化之前，也就是postProcessBeforeInitialization之前，
 * 这个类的触发点方法只有一个：setBeanName
 *
 * 使用场景为：用户可以扩展这个点，在初始化bean之前拿到spring容器中注册的的beanName，来自行修改这个beanName的值。
 *
 * 扩展方式为：
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 15:27
 **/
//@Component
public class BeanNameAwareTest implements BeanNameAware {
    public BeanNameAwareTest() {

    }

    @Override
    public void setBeanName(String name) {
    }
}
