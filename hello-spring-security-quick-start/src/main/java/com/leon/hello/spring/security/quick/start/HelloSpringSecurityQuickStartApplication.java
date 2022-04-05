package com.leon.hello.spring.security.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HelloSpringSecurityQuickStartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HelloSpringSecurityQuickStartApplication.class, args);
        System.out.println("run.getEnvironment().getProperty(\"server.port\") = " + run.getEnvironment().getProperty("server.port"));
    }

}
