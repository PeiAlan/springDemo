package com.ellison.springdemo.springStudy.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author ellisonpei
 */
public class CustomScope implements Scope {

    private final ThreadLocal<Object> local = new ThreadLocal<Object>();

    /**
     * 这个方法就是自己管理bean
     */
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("=============CustomScope========");
        Object objectBean = local.get();
        if (objectBean == null) {
            //这个方法就是掉createBean方法获得一个实例
            Object object = objectFactory.getObject();
            local.set(object);
            return object;
        } else {
            return objectBean;
        }
    }

    @Override
    public Object remove(String name) {
        Object scopedObject = local.get();
        if (scopedObject != null) {
            local.remove();
            return scopedObject;
        } else {
            return null;
        }
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
