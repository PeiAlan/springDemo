package com.ellison.springdemo.expands;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ApplicationContextInitializer 扩展点测试
 *
 * 这是整个spring容器在刷新之前初始化ConfigurableApplicationContext的回调接口，
 * 简单来说，就是在容器刷新之前调用此类的initialize方法。
 * 这个点允许被用户自己扩展。用户可以在整个spring容器还没被初始化之前做一些事情。
 *
 * 可以想到的场景可能为：
 * 在最开始激活一些配置，或者利用这时候class还没被类加载器加载的时机，进行动态字节码注入等操作。
 *
 * 因为这时候spring容器还没被初始化，所以想要自己的扩展的生效，有以下三种方式：
 *
 * 1、在启动类中用springApplication.addInitializers(new TestApplicationContextInitializer())语句加入
 * 2、配置文件配置context.initializer.classes=com.example.demo.TestApplicationContextInitializer
 * 3、Spring SPI扩展：在spring.factories中加入
 *          org.springframework.context.ApplicationContextInitializer=com.example.demo.TestApplicationContextInitializer
 *
 * @Author Ellison Pei
 * @Date 2020/10/14 09:34
 **/
//@Service
public class ApplicationContextInitializerTest implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("调用了自定义的ApplicationContextInitializerTest------initialize方法");
    }
}
