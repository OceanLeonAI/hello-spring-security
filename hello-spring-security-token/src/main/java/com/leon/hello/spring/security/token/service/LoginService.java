package com.leon.hello.spring.security.token.service;

import com.leon.hello.spring.security.token.domain.ResponseResult;
import com.leon.hello.spring.security.token.domain.User;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: LoginService
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/29 17:35
 * @Version 1.0
 * @DESCRIPTION:
 **/
public interface LoginService {

    /**
     * 登录接口
     *
     * @param user
     * @return
     */
    ResponseResult login(User user);
}
