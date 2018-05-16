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
    Gson gson;


    @RequestMapping( "/get-my-course" )
    @ResponseBody
    public String myCourse(@RequestAttribute("userTypeId") Integer studentId ) {

        List< StudentCourse > studentCourseList = studentCourseService.listStudentCourseByStudentId( studentId );
        List< Course > list = new ArrayList<>();
        for ( StudentCourse studentCourse : studentCourseList ) {
            Course c = courseService.getCourse( studentCourse.getCourseId() );
            list.add( c );
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
