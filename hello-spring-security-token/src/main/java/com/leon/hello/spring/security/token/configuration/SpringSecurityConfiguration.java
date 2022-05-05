package com.leon.hello.spring.security.token.configuration;

import com.leon.hello.spring.security.token.filter.JwtAuthenticationTokenFilter;
import com.leon.hello.spring.security.token.handler.AccessDeniedHandlerImpl;
import com.leon.hello.spring.security.token.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: SecurityConfiguraton
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/26 23:08
 * @Version 1.0
 * @DESCRIPTION: SpringSecurity 配置类
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 注入密码编码对象
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义拦截器
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 认证异常处理
     */
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    /**
     * 授权异常处理
     */
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    /**
     * security 配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous() // 未认证可以访问，认证后不能访问
                .antMatchers("/user/permitAll").permitAll() // 认证和未认证都可以访问
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated(); // 认证以后可以访问

        // 配置自定义拦截器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint) // 认证异常
                .accessDeniedHandler(accessDeniedHandler); // 授权异常
    }

    /**
     * 获取 AuthenticationManager 对象
     * <p>
     * Override this method to expose the AuthenticationManager
     * from configure(AuthenticationManagerBuilder)
     * to be exposed as a Bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
