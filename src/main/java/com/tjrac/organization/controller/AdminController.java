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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin-api")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    Gson gson;

    @RequestMapping( "/user-list" )
    @ResponseBody
    public String userList( ) {
        return gson.toJson( userService.listAll( ) );
    }


    @RequestMapping( "/admin-list" )
    @ResponseBody
    public String adminList( ) {
        return gson.toJson( adminService.listAll( ) );
    }

    @RequestMapping( "/student-list" )
    @ResponseBody
    public String studentList( ) {
        return gson.toJson( studentService.listAll( ) );
    }

    @RequestMapping( "/teacher-list" )
    @ResponseBody
    public String teacherList( ) {
        return gson.toJson( teacherService.listAll( ) );
    }

    @RequestMapping( "/add-admin" )
    @ResponseBody
    public String addAdmin(String userName,String userPwd, String adminName, String adminGender ,String adminTel,String adminAge,String adminMail,String adminWechat ,String adminQq) {
        JsonObject obj = new JsonObject();
        User user = new User();
        user.setUserName(userName);
        user.setUserPwd( EncryptionUtil.EncoderByMd5( userPwd ));
        user.setUserType( 1 );
        userService.saveUser(  user);
        user = userService.getUserByUserName( userName );
        Admin admin = new Admin();
        admin.setAdminName( adminName );
        admin.setAdminQq( adminQq );
        admin.setAdminWechat(  adminWechat);
        admin.setAdminTel( adminTel );
        admin.setAdminMail( adminMail );
        admin.setAdminGender( adminGender );
        admin.setAdminAge( adminAge );
        admin.setUserId( user.getUserId() );
        if ( adminService.saveAdmin( admin )&& user!=null  ) {
            obj.addProperty( "message", "添加成功！" );
        }else {
            obj.addProperty( "message", "添加失败！" );
        }

        return obj.toString();
    }

    @RequestMapping( "/update-admin" )
    @ResponseBody
    public String updateAdmin(int adminId , String adminName, String adminGender ,String adminTel,String adminAge,String adminMail,String adminWechat ,String adminQq ) {
        JsonObject obj = new JsonObject();

        Admin admin = adminService.getAdmin( adminId );

        admin.setAdminName( adminName );
        admin.setAdminQq( adminQq );
        admin.setAdminWechat(  adminWechat);
        admin.setAdminTel( adminTel );
        admin.setAdminMail( adminMail );
        admin.setAdminGender( adminGender );
        admin.setAdminAge( adminAge );
        if ( adminService.updateAdmin( admin )) {
            obj.addProperty( "message", "修改成功！" );
        }else {
            obj.addProperty( "message", "修改失败！" );
        }

        return obj.toString();
    }

    @RequestMapping( "/remove-admin" )
    @ResponseBody
    public String removeAdmin( int adminId) {
        JsonObject obj = new JsonObject();
        Admin admin = adminService.getAdmin( adminId );

        if ( adminService.removeAdmin( adminId )&&userService.removeUser( admin.getUserId() )) {
            obj.addProperty( "message", "删除成功！" );
        }else {
            obj.addProperty( "message", "删除失败！" );
        }
        return obj.toString();
    }

    @RequestMapping( "/get-admin" )
    @ResponseBody
    public String getAdmin( int adminId) {
        Admin admin = adminService.getAdmin( adminId );
        return gson.toJson( admin );
    }

    @RequestMapping( "/get-student" )
    @ResponseBody
    public String getStudent( int studentId) {
        Student student = studentService.getStudent( studentId );
        return gson.toJson( student );
    }

    @RequestMapping( "/get-teacher" )
    @ResponseBody
    public String getTeacher( int teacherId) {
        Teacher teacher = teacherService.getTeacher( teacherId );
        return gson.toJson( teacher );
    }



    @RequestMapping( "/update-student" )
    @ResponseBody
    public String updateStudent(int studentId , String studentName, String studentGender ,String studentTel,String studentAge,String studentMail,String studentWechat ,String studentQq,String studentMajor,String studentDepartment ) {
        JsonObject obj = new JsonObject();

        Student student = studentService.getStudent( studentId );

        student.setStudentName( studentName );
        student.setStudentQq( studentQq );
        student.setStudentWechat(  studentWechat);
        student.setStudentTel( studentTel );
        student.setStudentMail( studentMail );
        student.setStudentGender( studentGender );
        student.setStudentAge( studentAge );
        student.setStudentDepartment( studentDepartment );
        student.setStudentMajor(studentMajor);
        if ( studentService.updateStudent( student )) {
            obj.addProperty( "message", "修改成功！" );
        }else {
            obj.addProperty( "message", "修改失败！" );
        }

        return obj.toString();
    }

    @RequestMapping( "/add-student" )
    @ResponseBody
    public String addStudent(String studentNumber,String studentName, String studentGender ,String studentTel,String studentAge,String studentMail,String studentWechat ,String studentQq,String studentMajor,String studentDepartment ) {
        JsonObject obj = new JsonObject();
        User user = new User();


        if(userService.getUserByUserName( studentNumber )!=null){
            obj.addProperty( "message", "学号已存在！！" );
            return obj.toString();
        }

        user.setUserName(studentNumber );
        user.setUserPwd( EncryptionUtil.EncoderByMd5(  studentNumber  ));
        user.setUserType( 3 );
        userService.saveUser(  user);
        user = userService.getUserByUserName( studentNumber );
        Student student = new Student();
        student.setStudentNumber(  studentNumber  );
        student.setStudentName( studentName );
        student.setStudentQq( studentQq );
        student.setStudentWechat(  studentWechat);
        student.setStudentTel( studentTel );
        student.setStudentMail( studentMail );
        student.setStudentGender( studentGender );
        student.setStudentAge( studentAge );
        student.setStudentDepartment( studentDepartment );
        student.setStudentMajor(studentMajor);
        student.setUserId( user.getUserId() );
        if ( studentService.saveStudent( student )&& user!=null  ) {
            obj.addProperty( "message", "添加成功！" );
        }else {
            obj.addProperty( "message", "添加失败！" );
        }

        return obj.toString();
    }
    @RequestMapping( "/remove-student" )
    @ResponseBody
    public String removeStudent( int studentId) {
        JsonObject obj = new JsonObject();
        Student student = studentService.getStudent( studentId );

        if ( studentService.removeStudent( studentId )&&userService.removeUser( student.getUserId() )) {
            obj.addProperty( "message", "删除成功！" );
        }else {
            obj.addProperty( "message", "删除失败！" );
        }
        return obj.toString();
    }



    @RequestMapping( "/update-teacher" )
    @ResponseBody
    public String updateTeacher(int teacherId , String teacherName, String teacherGender ,String teacherTel,String teacherAge,String teacherMail,String teacherWechat ,String teacherQq ) {
        JsonObject obj = new JsonObject();

        Teacher teacher = teacherService.getTeacher( teacherId );
        teacher.setTeacherName( teacherName );
        teacher.setTeacherQq( teacherQq );
        teacher.setTeacherWechat(  teacherWechat);
        teacher.setTeacherTel( teacherTel );
        teacher.setTeacherMail( teacherMail );
        teacher.setTeacherGender( teacherGender );
        teacher.setTeacherAge( teacherAge );

        if ( teacherService.updateTeacher( teacher )) {
            obj.addProperty( "message", "修改成功！" );
        }else {
            obj.addProperty( "message", "修改失败！" );
        }

        return obj.toString();
    }

    @RequestMapping( "/add-teacher" )
    @ResponseBody
    public String addTeacher(String teacherNumber,String teacherName, String teacherGender ,String teacherTel,String teacherAge,String teacherMail,String teacherWechat ,String teacherQq) {
        JsonObject obj = new JsonObject();
        User user = new User();


        if(userService.getUserByUserName( teacherNumber )!=null){
            obj.addProperty( "message", "工号已存在！！" );
            return obj.toString();
        }

        user.setUserName(teacherNumber );
        user.setUserPwd( EncryptionUtil.EncoderByMd5(  teacherNumber  ));
        user.setUserType( 2 );
        userService.saveUser(  user);
        user = userService.getUserByUserName( teacherNumber );
        Teacher teacher = new Teacher();
        teacher.setTeacherNumber(  teacherNumber  );
        teacher.setTeacherName( teacherName );
        teacher.setTeacherQq( teacherQq );
        teacher.setTeacherWechat(  teacherWechat);
        teacher.setTeacherTel( teacherTel );
        teacher.setTeacherMail( teacherMail );
        teacher.setTeacherGender( teacherGender );
        teacher.setTeacherAge( teacherAge );

        teacher.setUserId( user.getUserId() );
        if ( teacherService.saveTeacher( teacher )&& user!=null  ) {
            obj.addProperty( "message", "添加成功！" );
        }else {
            obj.addProperty( "message", "添加失败！" );
        }

        return obj.toString();
    }
    @RequestMapping( "/remove-teacher" )
    @ResponseBody
    public String removeTeacher( int teacherId) {
        JsonObject obj = new JsonObject();
        Teacher teacher = teacherService.getTeacher( teacherId );

        if ( teacherService.removeTeacher( teacherId )&&userService.removeUser( teacher.getUserId() )) {
            obj.addProperty( "message", "删除成功！" );
        }else {
            obj.addProperty( "message", "删除失败！" );
        }
        return obj.toString();
    }
}