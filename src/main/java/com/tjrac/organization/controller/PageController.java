package com.tjrac.organization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping( "/login" )
    public String login( ) {
        return "login.html";
    }

    @RequestMapping( "/admin" )
    public String admin( ) {
        return "pages/index_admin.html";
    }

    @RequestMapping( "/teacher-admin" )
    public String teacherAdmin( ) {
        return "pages/teacher_admin.html";
    }

    @RequestMapping( "/student-admin" )
    public String studentAdmin( ) {
        return "pages/student_admin.html";
    }

    @RequestMapping( "/organization-admin" )
    public String organizationAdmin( ) {
        return "pages/organization_admin.html";
    }

    @RequestMapping( "/student" )
    public String newCourse( ) {
        return "pages/index_student.html";
    }

    @RequestMapping( "/new-course" )
    public String student( ) {
        return "pages/new_course.html";
    }

    @RequestMapping( "/teacher" )
    public String teacher( ) {
        return "pages/index_teacher.html";
    }
    @RequestMapping( "/my-application" )
    public String myApplication( ) {
        return "pages/my_application.html";
    }

    @RequestMapping("/my-grade")
    public String myGrade( ) {
        return "pages/my_grade.html";
    }

    @RequestMapping("/my-attendance")
    public String myAttendance( ) {
        return "pages/my_attendance.html";
    }

    @RequestMapping("/teacher-application")
    public String teacherApplication( ) {
        return "pages/teacher_application.html";
    }

    @RequestMapping("/student-list")
    public String studentList( ) {
        return "pages/student_list.html";
    }

    @RequestMapping("/attendance")
    public String attendance( ) {
        return "pages/attendance.html";
    }

    @RequestMapping("/grades")
    public String grades( ) {
        return "pages/grades.html";
    }

    @RequestMapping("/teacher-organization")
    public String teacherOrganization( ) {
        return "pages/teacher_organization.html";
    }
}

