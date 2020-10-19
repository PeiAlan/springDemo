package com.ellison.springdemo.expands;

import com.ellison.springdemo.expands.useful.MyMethodInterceptor;
import com.ellison.springdemo.expands.useful.TestFb;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

import java.beans.PropertyDescriptor;

/**
 * org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor
 * <p>
 * 该接口继承了BeanPostProcess接口，区别如下：
 * <p>
 * BeanPostProcess接口只在bean的初始化阶段进行扩展（注入spring上下文前后），
 * 而InstantiationAwareBeanPostProcessor接口在此基础上增加了3个方法，把可扩展的范围增加了实例化阶段和属性注入阶段。
 * <p>
 * 该类主要的扩展点有以下5个方法，主要在bean生命周期的两大阶段：实例化阶段和初始化阶段，下面一起进行说明，按调用顺序为：
 * <p>
 * postProcessBeforeInstantiation：实例化bean之前，相当于new这个bean之前
 * postProcessAfterInstantiation：实例化bean之后，相当于new这个bean之后
 * postProcessPropertyValues：bean已经实例化完成，在属性注入时阶段触发，@Autowired,@Resource等注解原理基于此方法实现
 * postProcessBeforeInitialization：初始化bean之前，相当于把bean注入spring上下文之前
 * postProcessAfterInitialization：初始化bean之后，相当于把bean注入spring上下文之后
 * <p>
 * 使用场景：这个扩展点非常有用 ，无论是写中间件和业务中，都能利用这个特性。
 * 比如对实现了某一类接口的bean在各个生命期间进行收集，或者对某个类型的bean进行统一的设值等等。
 * <p>
 * 扩展方式为：
 * <p>
 * 推荐博客：https://blog.csdn.net/u010634066/article/details/80321854
 *
 * @Author Ellison Pei
 * @Date 2020/10/15 11:20
 **/
//@Service
public class InstantiationAwareBeanPostProcessorTest implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[TestInstantiationAwareBeanPostProcessor] before initialization " + beanName);
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[TestInstantiationAwareBeanPostProcessor] after initialization " + beanName);
        return bean;
    }

    /**
     * 1、如果postProcessBeforeInstantiation方法返回了Object是null;那么就直接返回，调用doCreateBean方法();
     * 2、如果postProcessBeforeInstantiation返回不为null;说明修改了bean对象;然后这个时候就立马执行postProcessAfterInitialization方法(注意这个是初始化之后的方法,也就是通过这个方法实例化了之后，直接执行初始化之后的方法;中间的实例化之后 和 初始化之前都不执行);
     * 3、在调用postProcessAfterInitialization方法时候如果返回null;那么就直接返回，调用doCreateBean方法();(初始化之后的方法返回了null,那就需要调用doCreateBean生成对象了)
     * 4、在调用postProcessAfterInitialization时返回不为null;那这个bean就直接返回给ioc容器了 初始化之后的操作 是这里面最后一个方法了；
     * 综上，我们其实可以在这里生成一个代理类；
     * <p>
     * 在执行doCreateBean之前有resolveBeforeInstantiation方法；
     * doCreateBean是创建bean的方法；
     * resolveBeforeInstantiation是
     * 判断执行InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation的接方法实现
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        System.out.println("[TestInstantiationAwareBeanPostProcessor] before instantiation " + beanName);
//        return null;
        System.out.print("beanName:" + beanName + "执行..postProcessBeforeInstantiation\n");
        //利用 其 生成动态代理， 生成指定 bean---TestFb.class 的代理类
        if (beanClass == TestFb.class) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new MyMethodInterceptor());
            TestFb testFb = (TestFb) enhancer.create();
            System.out.print("返回动态代理\n");
            return testFb;
        }
        return null;

    }

    /**
     * 返回 false 可以使所有类不能ioc依赖注入
     * 若要正常就必须返回  true
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("[TestInstantiationAwareBeanPostProcessor] after instantiation " + beanName);
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
//        System.out.println("[TestInstantiationAwareBeanPostProcessor] postProcessPropertyValues " + beanName);
//        return pvs;
        // 修改 目标bean 的属性
        System.out.print("beanName:" + beanName + "执行..postProcessPropertyValues\n");
        if (bean instanceof TestFb) {
            //修改bean中a 的属性值
            PropertyValue value = pvs.getPropertyValue("a");
            System.out.print("修改之前 a 的value是：" + value.getValue() + "\n");
            value.setConvertedValue("我修改啦");
            return pvs;
        }
        return pvs;
    }
}

/**
 * 总结
 * 1、InstantiationAwareBeanPostProcessor接口继承BeanPostProcessor接口，它内部提供了3个方法，
 *      再加上BeanPostProcessor接口内部的2个方法，所以实现这个接口需要实现5个方法。
 *      InstantiationAwareBeanPostProcessor接口的主要作用在于目标对象的实例化过程中需要处理的事情，
 *      包括实例化对象的前后过程以及实例的属性设置。
 * 2、postProcessBeforeInstantiation方法是最先执行的方法，它在目标对象实例化之前调用，
 *      该方法的返回值类型是Object，我们可以返回任何类型的值。由于这个时候目标对象还未实例化，
 *      所以这个返回值可以用来代替原本该生成的目标对象的实例(比如代理对象)。
 *      如果该方法的返回值代替原本该生成的目标对象，后续只有postProcessAfterInitialization方法会调用，其它方法不再调用；
 *      否则按照正常的流程走。
 * 3、postProcessAfterInstantiation方法在目标对象实例化之后调用，这个时候对象已经被实例化，但是该实例的属性还未被设置，都是null。
 *      因为它的返回值是决定要不要调用postProcessPropertyValues方法的其中一个因素（因为还有一个因素是mbd.getDependencyCheck()）；
 *      如果该方法返回false,并且不需要check，那么postProcessPropertyValues就会被忽略不执行；
 *      如果返回true，postProcessPropertyValues就会被执行。
 * 4、postProcessPropertyValues方法对属性值进行修改(这个时候属性值还未被设置，但是我们可以修改原本该设置进去的属性值)。
 *      如果postProcessAfterInstantiation方法返回false，该方法可能不会被调用。可以在该方法内对属性值进行修改。
 * 5、父接口BeanPostProcessor的2个方法postProcessBeforeInitialization和postProcessAfterInitialization
 *      都是在目标对象被实例化之后，并且属性也被设置之后调用的。
 * 6、Instantiation表示实例化，Initialization表示初始化。实例化的意思在对象还未生成，初始化的意思在对象已经生成。
 */
