package com.leon.hello.spring.security.token.service.impl;

import com.leon.hello.spring.security.token.domain.LoginUser;
import com.leon.hello.spring.security.token.domain.ResponseResult;
import com.leon.hello.spring.security.token.domain.User;
import com.leon.hello.spring.security.token.service.LoginService;
import com.leon.hello.spring.security.token.utils.JwtUtil;
import com.leon.hello.spring.security.token.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: LoginServiceImpl
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/29 17:35
 * @Version 1.0
 * @DESCRIPTION:
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录接口
     *
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {

        Object principal = user.getUserName(); // 用户名
        Object credentials = user.getPassword(); // 密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, credentials);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);

        // authenticate存入redis
        redisCache.setCacheObject("login:" + id, loginUser);

        // 把token响应给前端
        return new ResponseResult(200, "登陆成功", jwt);
    }
}
