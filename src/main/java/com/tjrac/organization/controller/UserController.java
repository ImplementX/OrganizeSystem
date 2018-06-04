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
import com.tjrac.organization.util.EncryptionUtil;
import com.tjrac.organization.util.JwtHelper;
import jdk.nashorn.internal.ir.SplitReturn;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        User user = userService.getUserByUserName( username );

        JsonObject json = new JsonObject( );
        String nickname = "";
        if ( user == null ) {
            json.addProperty( "message", "用户不存在！！" );
            json.addProperty( "user", "" );
        }else  if(EncryptionUtil.EncoderByMd5(password).equals(user.getUserPwd()) ){
            json.addProperty( "user", gson.toJson( user ) );
            int userId = user.getUserId( );
            int userTypeId = -1;
//            mv.addObject("message","登录成功！");
            int userType = user.getUserType( );
            switch ( userType ) {
                case 1:
                    json.addProperty( "page", "admin" );
                    Admin admin = adminService.getAdminByUserId( userId );
                    userTypeId = admin.getAdminId( );
                    nickname = admin.getAdminName( );
                    json.addProperty( "nickname", nickname + " 管理员" );
                    break;
                case 2:
                    json.addProperty( "page", "teacher" );
                    Teacher teacher = teacherService.getTeacherByUserId( userId );
                    userTypeId = teacher.getTeacherId( );
                    nickname = teacher.getTeacherName( );
                    json.addProperty( "nickname", nickname + " 老师" );
                    break;
                case 3:
                    json.addProperty( "page", "student" );
                    Student student = studentService.getStudentByUserId( userId );
                    userTypeId = student.getStudentId( );
                    nickname = student.getStudentName( );
                    json.addProperty( "nickname", nickname + " 同学" );
                    break;
                default:
                    json.addProperty( "message", "未知用户类型" );
                    break;
            }

            json.addProperty( "key", JwtHelper.createJWT( userId, username, userType, userTypeId ) );



        }else {
            json.addProperty( "message", "账号或密码错误！！！" );
            json.addProperty( "user", "" );
        }

        return json.toString( );
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


    @RequestMapping( "/change-password" )
    @ResponseBody
    public String changePassword( @RequestAttribute( "myUserId" ) int myUserId, int userId, String oldPwd, String newPwd ) {
        JsonObject obj = new JsonObject( );
        User me = userService.getUser( myUserId);
        User user = userService.getUser( userId );


        if ( me.getUserType( ) == 3 ) {
            return passwordChanger( me, user, obj, newPwd, oldPwd );

        } else if ( me.getUserType( ) == 2 ) {
            if ( user.getUserType( ) == 3 ) {
                return passwordChanger(user, obj, newPwd );
            } else {
                return passwordChanger( me, user, obj, newPwd, oldPwd );
            }

        } else {
            return passwordChanger( user, obj, newPwd );
        }
    }

    private String passwordChanger( User me, User user, JsonObject obj, String newPwd, String oldPwd ) {
        String pwd = user.getUserPwd( );

        if ( pwd != EncryptionUtil.EncoderByMd5( oldPwd ) ) {
            obj.addProperty( "message", "旧密码错误！" );
            return obj.toString( );
        } else {
            user.setUserPwd( EncryptionUtil.EncoderByMd5( newPwd ) );


            if ( userService.updateUser( user ) ) {
                obj.addProperty( "message", "修改成功！" );
            } else {
                obj.addProperty( "message", "修改失败！" );
            }
            return obj.toString( );
        }


    }

    private String passwordChanger( User user, JsonObject obj, String newPwd ) {

        user.setUserPwd( EncryptionUtil.EncoderByMd5( newPwd ) );

        if ( userService.updateUser( user ) ) {
            obj.addProperty( "message", "修改成功！" );
        } else {
            obj.addProperty( "message", "修改失败！" );
        }
        return obj.toString( );

    }
}
