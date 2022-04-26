package com.leon.hello.spring.security.token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.leon.hello.spring.security.token.mapper")
@ComponentScan(basePackages = "com.leon.hello.spring.security.token.*")
public class HelloSpringSecurityTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringSecurityTokenApplication.class, args);
    }

}
