package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tjrac.organization.pojo.Admin;
import com.tjrac.organization.pojo.Student;
import com.tjrac.organization.pojo.Teacher;
import com.tjrac.organization.pojo.User;
import com.tjrac.organization.service.AdminService;
import com.tjrac.organization.service.StudentService;
import com.tjrac.organization.service.TeacherService;
import com.tjrac.organization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URL;


@RestController
@RequestMapping( "/user" )
@SessionAttributes( "user" )
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    AdminService adminService;
    @Autowired
    Gson gson;


    @RequestMapping( "/login" )
    public String login( String username, String password, HttpSession session ) {
        User user = userService.isUser( username, password );
        JsonObject json = new JsonObject();
        String name;
        if ( user != null ) {
            json.addProperty( "user", gson.toJson( user ) );
            int userId = user.getUserId();
            session.setAttribute( "userId", userId );
//            mv.addObject("message","登录成功！");
            int userType = user.getUserType();
            if ( userType == 1 ) {
                json.addProperty( "page", "admin" );
                Admin admin = adminService.getAdminByUserId( userId );
                session.setAttribute( "userName", admin.getAdminName() );

            } else if ( userType == 2 ) {
                json.addProperty( "page", "teacher" );
                Teacher teacher = teacherService.getTeacherByUserId( userId );
                session.setAttribute( "userName", teacher.getTeacherName() );
            } else if ( userType == 3 ) {
                json.addProperty( "page", "student" );
                Student student = studentService.getStudentByUserId( userId );
                session.setAttribute( "userName", student.getStudentName() );
            } else {
                json.addProperty( "message", "未知用户类型" );
                json.addProperty( "user", "" );
            }


        } else {

            json.addProperty( "message", "用户名或密码错误！" );
            json.addProperty( "user", "" );
        }
        return json.toString();
    }


//    @RequestMapping("/register")
//    @ResponseBody
//    public String register(Model model, String  username, String password){
//        User user = userService.isUser(username, password);
//
//        if(user != null){
//            model.addAttribute("user",user);
//            return "redirect:index_admin.html";
//        }
//        return "用户名或密码错误！";
//    }


    @RequestMapping( "/" )
    @ResponseBody
    public String hello( String test ) {
        return test;
    }


}
