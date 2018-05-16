package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.tjrac.organization.pojo.Admin;
import com.tjrac.organization.service.AdminService;
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
    Gson gson;
    @RequestMapping("/admin-list")
    @ResponseBody
    public String adminList(){
        return gson.toJson(adminService.listAll());
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(int adminId){
        return adminService.removeAdmin(adminId)?"删除成功！":"删除失败！";
    }

    @RequestMapping("/add")
    @ResponseBody
    public  String add(String newAdmin){
        return adminService.saveAdmin(gson.fromJson(newAdmin, Admin.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(String thisAdmin){
        return adminService.saveAdmin(gson.fromJson(thisAdmin, Admin.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/find")
    @ResponseBody
    public String find(int userId){
        return gson.toJson(adminService.getAdmin(userId));
    }
}
