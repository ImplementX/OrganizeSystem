package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.tjrac.organization.pojo.Teacher;
import com.tjrac.organization.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    Gson gson;

    @RequestMapping("/teacher-list")
    @ResponseBody
    public String teacherList(){
        return gson.toJson(teacherService.listAll());
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(int teacherId){
        return teacherService.removeTeacher(teacherId)?"删除成功！":"删除失败！";
    }

    @RequestMapping("/add")
    @ResponseBody
    public  String add(String newTeacher){
        return teacherService.saveTeacher(gson.fromJson(newTeacher, Teacher.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(String thisTeacher){
        return teacherService.saveTeacher(gson.fromJson(thisTeacher, Teacher.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/find")
    @ResponseBody
    public String find(int userId){
        return gson.toJson(teacherService.getTeacher(userId));
    }
    
}
