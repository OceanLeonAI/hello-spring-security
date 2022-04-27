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
        System.out.println("encode = " + encode); // $2a$10$aAr9QYA0Vi84BHv.EUSydOAEN1O3QL594E2zzKVo98q5u1v9Ev0by
        System.out.println("bCryptPasswordEncoder.matches(password, encode) = " + bCryptPasswordEncoder.matches(password, encode));

        System.out.println("-----------------------------------");

        String encode1 = bCryptPasswordEncoder.encode(password);
        System.out.println("encode1 = " + encode1); // $2a$10$wDRXQ/ndzJrm3v.B2inyteZpyA9aUK4PRYJPH7/wWsY9z/N.AIUeC
        System.out.println("bCryptPasswordEncoder.matches(password, encode1) = " + bCryptPasswordEncoder.matches(password, encode1));

    }
}
