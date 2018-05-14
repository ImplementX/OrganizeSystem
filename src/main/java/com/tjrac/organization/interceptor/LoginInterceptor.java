package com.tjrac.organization.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        HttpSession session = request.getSession();
        Integer userId= ( Integer ) session.getAttribute( "userId" );
        if ( userId==null ){
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
        }else {
            return true;
        }


    }
}
