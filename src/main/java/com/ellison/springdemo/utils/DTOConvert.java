package com.ellison.springdemo.utils;

/**
 * <p>转化接口</p>
 *
 * @author Ellison Pei
 * @Date 2020/8/9 21:50
 **/
public interface DTOConvert<S,T> {
    T convert(S s);
}
