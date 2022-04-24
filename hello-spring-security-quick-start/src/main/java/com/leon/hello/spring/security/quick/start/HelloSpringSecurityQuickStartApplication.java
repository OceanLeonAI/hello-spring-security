package com.leon.hello.spring.security.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringSecurity 入门
 * <p>
 * 登录
 * http://localhost:8080
 * user/控制台打印密码
 * <p>
 * 登出
 * http://localhost:8080/logout
 */
@SpringBootApplication
public class HelloSpringSecurityQuickStartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HelloSpringSecurityQuickStartApplication.class, args);
        System.out.println("run.getEnvironment().getProperty(\"server.port\") = " + run.getEnvironment().getProperty("server.port"));
    }

}
