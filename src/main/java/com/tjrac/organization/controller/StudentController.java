package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.tjrac.organization.pojo.Student;
import com.tjrac.organization.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    Gson gson;
    @RequestMapping("/student-list")
    @ResponseBody
    public String studentList(){
        return gson.toJson(studentService.listAll());
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(int studentId){
        return studentService.removeStudent(studentId)?"删除成功！":"删除失败！";
    }

    @RequestMapping("/add")
    @ResponseBody
    public  String add(String newStudent){
        return studentService.saveStudent(gson.fromJson(newStudent, Student.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(String thisStudent){
        return studentService.saveStudent(gson.fromJson(thisStudent, Student.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/find")
    @ResponseBody
    public String find(int userId){
        return gson.toJson(studentService.getStudent(userId));
    }



}
