package com.leon.hello.spring.security.token;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: BCryptPasswordEncoderTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/26 23:11
 * @Version 1.0
 * @DESCRIPTION:
 **/
public class BCryptPasswordEncoderTest {

    @Test
    public void encode() {
        String password = "123";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        System.out.println("encode = " + encode);
    }
}
