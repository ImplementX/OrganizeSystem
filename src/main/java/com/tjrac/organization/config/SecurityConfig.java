package com.tjrac.organization.config;

import com.tjrac.organization.interceptor.AuthenticationFilter;
import com.tjrac.organization.interceptor.LoginFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig( UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers( HttpMethod.POST, "/users/login","/build/**", "/vendors/**", "/about").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new LoginFilter(authenticationManager()))
                .addFilter(new AuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {//FilterSecurityInterceptor拦截器做配置
//        http.authorizeRequests().antMatchers("/build/**", "/vendors/**", "/about").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/teacher/**").hasAnyRole("TEACHER" ,"ADMIN" )
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage( "/login" ).permitAll();
//
//    }


}