package com.leon.hello.spring.security.token.controller;

import com.leon.hello.spring.security.token.domain.ResponseResult;
import com.leon.hello.spring.security.token.domain.User;
import com.leon.hello.spring.security.token.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: LoginController
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/29 11:18
 * @Version 1.0
 * @DESCRIPTION: 登录接口
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * TODO: 已登录状态再次登录
     *
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
//        if(true){
//            throw new RuntimeException("登录时抛出异常");
//        }
        return loginService.login(user);
    }


    /**
     * 登出接口
     *
     * @return
     */
    @PostMapping("/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}
