package com.ellison.springdemo.expands;

import com.ellison.springdemo.expands.useful.BeanClass;
import com.ellison.springdemo.expands.useful.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * 这个接口在读取项目中的beanDefinition之后执行，提供一个补充的扩展点
 * 使用场景：你可以在这里动态注册自己的beanDefinition，可以加载classpath之外的bean
 *
 * 扩展方式如下
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 08:57
 **/
//@Component
//@Service
public class BeanDefinitionRegistryPostProcessorTest implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //修改定义自己的 beanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(BeanClass.class);

//        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(null);
        //修改 beanDefinition 的属性
        MutablePropertyValues propertyValues = genericBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("username","ellison");
        //注册修改后的 beanDefinition
        registry.registerBeanDefinition("beanClass",genericBeanDefinition);

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyService.class));
        scanner.scan("com.ellison.springdemo");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
