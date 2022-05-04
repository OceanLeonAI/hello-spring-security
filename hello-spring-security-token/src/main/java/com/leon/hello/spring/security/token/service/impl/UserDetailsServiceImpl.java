package com.leon.hello.spring.security.token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.leon.hello.spring.security.token.domain.LoginUser;
import com.leon.hello.spring.security.token.domain.Menu;
import com.leon.hello.spring.security.token.domain.User;
import com.leon.hello.spring.security.token.mapper.MenuMapper;
import com.leon.hello.spring.security.token.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: UserDetailsServiceImpl
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/24 22:08
 * @Version 1.0
 * @DESCRIPTION: 创建一个类实现UserDetailsService接口，重写其中的方法。更加用户名从数据库中查询用户信息
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 构造查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        //如果没有查询到用户就抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        // TODO: 根据用户信息查询其对应权限信息
        // List<String> permissions = Arrays.asList("test", "admin");
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());


        // 封装返回数据类型
        return new LoginUser(user, permissions);
    }
}
