package com.tjrac.organization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping( "login" )
    public String login( ) {
        return "login.html";
    }

    @RequestMapping( "admin" )
    public String admin( ) {
        return "/pages/index_admin.html";
    }

    @RequestMapping( "student" )
    public String student( ) {
        return "pages/index_student.html";
    }

    @RequestMapping( "teacher" )
    public String teacher( ) {
        return "pages/index_teacher.html";
    }
}

