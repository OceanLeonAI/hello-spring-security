package com.leon.hello.spring.security.token;

import com.leon.hello.spring.security.token.domain.User;
import com.leon.hello.spring.security.token.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: MapperTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/24 22:00
 * @Version 1.0
 * @DESCRIPTION:
 **/
@SpringBootTest

public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试用户信息Mapper
     */
    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
