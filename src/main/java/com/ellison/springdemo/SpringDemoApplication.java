package com.ellison.springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

/**
 * @author ellisonpei
 */
@EnableCaching
@MapperScan(basePackages = {"com.ellison.springdemo.mapper"},annotationClass = Component.class)
@SpringBootApplication
public class SpringDemoApplication {
    public static void main(String[] args) {

//        SpringApplication springApplication = new SpringApplication(SpringDemoApplication.class);
//        springApplication.addInitializers(new ApplicationContextInitializerTest());

        SpringApplication.run(SpringDemoApplication.class, args);
    }


}
