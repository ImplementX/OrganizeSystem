package com.tjrac.organization.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {//FilterSecurityInterceptor拦截器做配置
        http.authorizeRequests().antMatchers("/build/**", "/vendors/**", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/teacher/**").hasAnyRole("TEACHER" ,"ADMIN" )
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage( "/login" ).permitAll();

    }


}