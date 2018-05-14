package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.tjrac.organization.pojo.Course;
import com.tjrac.organization.pojo.CourseApplication;
import com.tjrac.organization.pojo.Student;
import com.tjrac.organization.pojo.StudentCourse;
import com.tjrac.organization.service.CourseApplicationService;
import com.tjrac.organization.service.CourseService;
import com.tjrac.organization.service.StudentCourseService;
import com.tjrac.organization.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
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
    Gson gson;


    @RequestMapping( "/get-my-course" )
    public String myCourse( int studentId ) {
        List< StudentCourse > studentCourseList = studentCourseService.listStudentCourseByStudentId( studentId );
        List< Course > list = new ArrayList<>();
        for ( StudentCourse studentCourse : studentCourseList ) {
            Course c = courseService.getCourse( studentCourse.getCourseId() );
            list.add( c );
        }

        return gson.toJson(list);
    }

    @RequestMapping("/get-new-course")
    public String newCourse(){
     return  gson.toJson( courseService.listCourseNotFull() ) ;
    }

    @RequestMapping("/get-my-application")
    public String myApplication( HttpSession session){
        int userId = (int)session.getAttribute( "userId" );
        return  gson.toJson( courseApplicationService.listByStudentId( userId ) )  ;
    }




}
