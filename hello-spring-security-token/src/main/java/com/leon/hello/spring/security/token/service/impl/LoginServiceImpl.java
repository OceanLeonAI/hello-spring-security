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
import org.springframework.security.core.context.SecurityContextHolder;
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
        // AuthenticationManager 认证
        Object principal = user.getUserName(); // 用户名
        Object credentials = user.getPassword(); // 密码
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, credentials);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();

        String cacheKey = "login:" + id;

        // 判断是否已经登录过了
        LoginUser loginUserExist = redisCache.getCacheObject(cacheKey);
        if (!Objects.isNull(loginUserExist)) {
            throw new RuntimeException("用户已登录");
        }

        String jwt = JwtUtil.createJWT(id);

        // 完整的用户信息存入redis
        redisCache.setCacheObject(cacheKey, loginUser);

        // 把token响应给前端
        return new ResponseResult(200, "登录成功", jwt);
    }

    /**
     * 登出接口
     * 获取SecurityContextHolder中的认证信息，
     * 删除redis中对应的数据即可
     *
     * @return
     */
    @Override
    public ResponseResult logout() {

        // 获取 SecurityContextHolder 用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        /**
         * 此处无需校验获取的用户是否为空
         * 因为如果为空会被前置的拦截器处理掉，不会进入该环节
         */
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String cacheKey = "login:" + loginUser.getUser().getId();
        redisCache.deleteObject(cacheKey);

        return new ResponseResult(200, "退出成功");
    }
}
