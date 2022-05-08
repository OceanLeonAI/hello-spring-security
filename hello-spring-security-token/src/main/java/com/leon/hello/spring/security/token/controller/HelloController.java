package com.leon.hello.spring.security.token.controller;

import com.leon.hello.spring.security.token.configuration.SpringSecurityConfiguration;
import com.leon.hello.spring.security.token.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
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

    // @PreAuthorize("hasAnyAuthority('test')")
    @PreAuthorize("@customisedSecurityExpressionRoot.hasAuthority('test')") // 注意引用自定义bean方法
    // @PreAuthorize("hasAnyAuthority('admin','test','system:dept:list')")
    // @PreAuthorize("hasRole('system:dept:list')")
    // @PreAuthorize("hasAnyRole('admin','system:dept:list')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * TODO: 增加 spring security 权限相关模拟方法
     *
     * @see AbstractRequestMatcherRegistry#antMatchers(java.lang.String...)
     * {@link SpringSecurityConfiguration#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)}
     */

    @RequestMapping("/testCors")
    public ResponseResult testCors() {
        return new ResponseResult(200, "testCors");
    }

}
