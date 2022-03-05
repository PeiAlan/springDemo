package com.ellison.springdemo.springStudy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>TODO</p>
 *
 * @author Ellison_Pei
 * @date 2022/3/5 16:06
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ComponentScan.class);
        //annotationConfigApplicationContext.getBean();
    }
}
