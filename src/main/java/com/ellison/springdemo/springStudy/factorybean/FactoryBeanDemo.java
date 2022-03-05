package com.ellison.springdemo.springStudy.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * org.springframework.beans.factory.FactoryBean
 *
 * 一般情况下，Spring通过反射机制利用bean的class属性指定支线类去实例化bean，
 * 在某些情况下，实例化Bean过程比较复杂，如果按照传统的方式，则需要在bean中提供大量的配置信息。
 * 配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。
 *
 * Spring为此提供了一个 org.springframework.bean.factory.FactoryBean 的工厂类接口，
 * 用户可以通过实现该接口定制实例化Bean的逻辑。
 * FactoryBean接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。
 * 它们隐藏了实例化一些复杂bean的细节，给上层应用带来了便利。
 * 从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean的形式
 *
 * 使用场景：用户可以扩展这个类，来为要实例化的bean作一个代理，
 * 比如为该对象的所有的方法作一个拦截，在调用，前后输出一行log模仿ProxyFactoryBean的功能。
 *
 * 扩展方式为：implements FactoryBean{}   , 然后重写 getObject(), getObjectType()
 *
 * 这个扩展点在 getSingleton(String beanName, ObjectFactory<?> singletonFactory) 创建完bean实例之后调用
 * 相当于对已创建的bean实例不太满意，想要自己创建，
 *
 * @author Ellison_Pei
 * @date 2022/3/4 14:03
 * @since 1.0
 **/
//@Service
public class FactoryBeanDemo implements FactoryBean {
    /**
     * 返回的实例 也是 spring管理的
     * 这里也可以 返回一个代理实例，比如dubbo的处理就是 在这返回了一个代理实例
     * @return
     * @throws Exception
     */
    @Override
    public Object getObject() throws Exception {
        // getObject创建新的bean 不影响FactoryBeanDemo 这个bean对象
        return new FactoryB();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryB.class;
    }
}
