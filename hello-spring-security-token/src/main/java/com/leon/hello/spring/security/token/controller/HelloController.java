package com.leon.hello.spring.security.token.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: HelloController
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/26 22:31
 * @Version 1.0
 * @DESCRIPTION: hello world
 **/
@RestController
public class HelloController {

    @PreAuthorize("hasAnyAuthority('test')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
