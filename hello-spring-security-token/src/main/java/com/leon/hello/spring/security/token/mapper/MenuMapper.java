package com.leon.hello.spring.security.token.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leon.hello.spring.security.token.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: MenuMapper
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/4 23:02
 * @Version 1.0
 * @DESCRIPTION: 菜单 mapper
 **/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(@Param("userid") Long userid);

}
