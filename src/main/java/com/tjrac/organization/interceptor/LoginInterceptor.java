package com.tjrac.organization.interceptor;

import com.tjrac.organization.util.JwtHelper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        String key = request.getHeader( "JWTKey" );
        System.out.println("get -----key " +key);
        Map< String, Object > map = JwtHelper.parseJWT( key );
        if ( key!=null ){
            System.out.println( map.toString());
            Integer userId = ( Integer ) map.get( "user_id" );
            Integer userType = ( Integer ) map.get( "user_type" );
            Integer userTypeId = ( Integer ) map.get( "user_type_id" );
            System.out.println(userId+" "+userTypeId);
            request.setAttribute( "userTypeId" ,userTypeId);
            request.setAttribute( "myUserId",userId);
            String path = request.getContextPath();
            if ( userType==3 ){
                if ( path.contains( "teacher-api" )|| path.contains( "admin-api" )){
                    return false;
                }
            }else if ( userType==2 ){
                if (path.contains( "admin-api" )  ){
                    return false;
                }
            }
            return true;
        }

        return false;

    }

}
