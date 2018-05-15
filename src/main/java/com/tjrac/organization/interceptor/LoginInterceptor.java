package com.tjrac.organization.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tjrac.organization.pojo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LoginInterceptor extends UsernamePasswordAuthenticationFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    Gson gson;

//            @Override
//            public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
//                HttpSession session = request.getSession();
//                Integer userId= ( Integer ) session.getAttribute( "userId" );
//                System.out.println("拦截器被执行");
//                if ( userId==null ){
//                    response.sendRedirect(request.getContextPath()+"/login");
//
//                    System.out.println(request.getRequestURI()+"被拦截 ");
//                    return false;
//                }else {
//                    return true;
//        }

    private AuthenticationManager authenticationManager;

    public LoginInterceptor(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        String s= request.
        User user = gson.fromJson(s,User.class );

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),
                        user.getUserPwd(),
                        new ArrayList<>())
        );
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith( SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
    }


}
}
