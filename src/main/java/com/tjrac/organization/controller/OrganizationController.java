package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.tjrac.organization.pojo.Organization;
import com.tjrac.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;
    @Autowired
    Gson gson;

    @RequestMapping("/organization-list")
    @ResponseBody
    public String organizationList(){
        return gson.toJson(organizationService.listAll());
    }

    @RequestMapping("/find-by-tag")
    @ResponseBody
    public String find(String tags){
        return gson.toJson(organizationService.listOrganizationByTags(tags));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(int organizationId){
        return organizationService.removeOrganization(organizationId)?"删除成功！":"删除失败！";
    }

    @RequestMapping("/add")
    @ResponseBody
    public  String add(String newOrganization){
        return organizationService.saveOrganization(gson.fromJson(newOrganization, Organization.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(String thisOrganization){
        return organizationService.saveOrganization(gson.fromJson(thisOrganization, Organization.class))?"添加成功！":"添加失败！";
    }

    @RequestMapping("/find")
    @ResponseBody
    public String find(int organizationId){
        return gson.toJson(organizationService.getOrganization(organizationId));
    }


}
