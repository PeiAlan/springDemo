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
@MapperScan(basePackages = {"com.ellison.springdemo.common.mapper"}, annotationClass = Component.class)
@SpringBootApplication
public class SpringDemoApplication {
    public static void main(String[] args) {

        //SpringApplication springApplication = new SpringApplication(SpringDemoApplication.class);
        //springApplication.addInitializers(new ApplicationContextInitializerTest());
        //// 通过扩展点 设置禁止循环依赖
        //springApplication.addInitializers(new ApplicationContextInitializerTest2());

        SpringApplication.run(SpringDemoApplication.class, args);
    }


}
