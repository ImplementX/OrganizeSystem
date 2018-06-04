package com.tjrac.organization.config;

import com.tjrac.organization.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        registry.addInterceptor( new LoginInterceptor() ).addPathPatterns("/**")
                .excludePathPatterns( "/*","/**.html" )
        .excludePathPatterns( "/build/**" ,"/vendors/**")
        .excludePathPatterns( "/user/login","/pages/**" )
       ;
    }

//    @Override
//    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
//        registry.addResourceHandler( "classpath:/build/" ,"classpath:/vendors/","classpath:/error/");
//    }
}
