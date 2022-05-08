package com.leon.hello.spring.security.token.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: CorsConfig
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/8 21:56
 * @Version 1.0
 * @DESCRIPTION: SpringBoot 允许跨域访问相关设置
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 添加跨域设置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                // 设置允许跨域的路径
                .addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
