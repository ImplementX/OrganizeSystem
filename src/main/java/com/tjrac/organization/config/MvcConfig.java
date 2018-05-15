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
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/pages/**")
                .excludePathPatterns("/login","/login.html");

    }


//    @Override
//    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
//        registry.addResourceHandler("/build/**").addResourceLocations("classpath:/build/");
//        registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/vendors/");
//    }
}
