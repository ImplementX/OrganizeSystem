package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tjrac.organization.pojo.*;
import com.tjrac.organization.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/student-api")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentCourseService studentCourseService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseApplicationService courseApplicationService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    Gson gson;


    @RequestMapping( "/get-my-course" )
    @ResponseBody
    public String myCourse(@RequestAttribute("userTypeId") Integer studentId ) {

        List< StudentCourse > studentCourseList = studentCourseService.listStudentCourseByStudentId( studentId );
        List< JsonObject > list = new ArrayList<>();
        for ( StudentCourse studentCourse : studentCourseList ) {
            Course c = courseService.getCourse( studentCourse.getCourseId() );
            JsonObject obj = new JsonObject();
            Organization organization = organizationService.getOrganization( c.getOrganizationId() );
            Teacher teacher = teacherService.getTeacher( c.getTeacherId() );
            obj.addProperty( "courseName",c.getCourseName() );
            obj.addProperty( "courseTag",c.getCourseTag() );
            obj.addProperty( "organizationName", organization.getOrganizationName());
            obj.addProperty( "teacherName",teacher.getTeacherName() );
            obj.addProperty( "teacherTel",teacher.getTeacherTel() );
            obj.addProperty( c.get );

        }

        return gson.toJson(list);
    }

    @RequestMapping("/get-new-course")
    @ResponseBody
    public String newCourse(@RequestAttribute("userTypeId") Integer studentId){
        HashSet<Integer> removeSet = new HashSet<>(  );
        List< StudentCourse > studentCourses = studentCourseService.listStudentCourseByStudentId( studentId );

        for ( StudentCourse sc : studentCourses ) {
            removeSet.add( sc.getCourseId() );
        }
        List< CourseApplication > courseApplications =  courseApplicationService.listByStudentId(  studentId );
        for ( CourseApplication ca : courseApplications ) {
            removeSet.add( ca.getCourseId() );
        }
       List<Course> result =  courseService.listCourseNotFull();
        for ( Course course : result ) {
           if ( removeSet.contains( course.getCourseId() ) ){
               result.remove( course );
           }
        }

     return  gson.toJson( result ) ;
    }

    @RequestMapping("/get-my-application")
    @ResponseBody
    public String myApplication(@RequestAttribute("userTypeId") int studentId){

        return  gson.toJson( courseApplicationService.listByStudentId( studentId ) )  ;
    }




}
