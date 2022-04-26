package com.leon.hello.spring.security.token.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: SecurityConfiguraton
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/26 23:08
 * @Version 1.0
 * @DESCRIPTION:
 **/
@Configuration
public class SpringSecurityConfiguraton extends WebSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
