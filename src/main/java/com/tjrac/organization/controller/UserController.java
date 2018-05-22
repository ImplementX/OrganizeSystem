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
import com.tjrac.organization.util.JwtHelper;
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
        String nickname = "";
        if ( user != null ) {
            json.addProperty( "user", gson.toJson( user ) );
            int userId = user.getUserId();
            int userTypeId = -1;
//            mv.addObject("message","登录成功！");
            int userType = user.getUserType();
            switch ( userType ){
                case 1:
                    json.addProperty( "page", "admin");
                    Admin admin = adminService.getAdminByUserId( userId );
                    userTypeId =admin.getAdminId();
                    nickname = admin.getAdminName();
                    break;
                case 2:
                    json.addProperty( "page", "teacher" );
                    Teacher teacher = teacherService.getTeacherByUserId( userId );
                    userTypeId = teacher.getTeacherId();
                    nickname = teacher.getTeacherName();
                    break;
                case 3: json.addProperty( "page", "student");
                    Student student = studentService.getStudentByUserId( userId );
                    userTypeId = student.getStudentId();
                    nickname = student.getStudentName();
                break;
                default: json.addProperty( "message", "未知用户类型" );

                break;
            }
            json.addProperty( "nickname",nickname );
            json.addProperty( "key",JwtHelper.createJWT( userId,username,userType ,userTypeId) );
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
