package com.tjrac.organization.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tjrac.organization.pojo.User;
import com.tjrac.organization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;


@RestController
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Gson gson;


    @RequestMapping("/login")
    public String login(String  username, String password){
        User user = userService.isUser(username, password);
        JsonObject json = new JsonObject();
        if(user != null){
            json.addProperty("user", gson.toJson( user ));
//            mv.addObject("message","登录成功！");
            switch (user.getUserType()){
                case 1:json.addProperty("page","admin");break;
                case 2:json.addProperty("page","teacher");break;
                case 3:json.addProperty("page","student");break;
            }

        }else {

            json.addProperty("message","用户名或密码错误！");
            json.addProperty("user", "");
        }
        return json.toString();
    }



//    @RequestMapping("/register")
//    @ResponseBody
//    public String register(Model model, String  username, String password){
//        User user = userService.isUser(username, password);
//
//        if(user != null){
//            model.addAttribute("user",user);
//            return "redirect:index_admin.html";
//        }
//        return "用户名或密码错误！";
//    }


    @RequestMapping("/")
    @ResponseBody
    public String hello(String test){
        return test;
    }


}
