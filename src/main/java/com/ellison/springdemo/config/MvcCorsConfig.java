package com.ellison.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域全局配置，指定uri  在 /cors  下的所有方法进行跨域设置
 *
 * 跨域配置详情：主要是类  CorsRegistration  进行控制
 * registry.addMapping("/api/**")
 *             .allowedOrigins("http://domain2.com")
 *             .allowedMethods("PUT", "DELETE")
 *             .allowedHeaders("header1", "header2", "header3")
 *             .exposedHeaders("header1", "header2")
 *             .allowCredentials(false).maxAge(3600);
 *
 * @author ellisonpei
 */
@Configuration
public class MvcCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cors/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET","DELETE")
                .allowedHeaders("*");
    }
}
