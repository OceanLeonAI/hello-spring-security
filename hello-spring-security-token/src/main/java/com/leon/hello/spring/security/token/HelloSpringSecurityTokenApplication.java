package com.leon.hello.spring.security.token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.leon.hello.spring.security.token.*")
public class HelloSpringSecurityTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringSecurityTokenApplication.class, args);
    }

}
