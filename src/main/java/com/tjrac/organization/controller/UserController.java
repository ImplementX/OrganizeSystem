package com.tjrac.organization.controller;

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

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView mv, String  username, String password){
        User user = userService.isUser(username, password);

        if(user != null){
            mv.addObject("user", user);
//            mv.addObject("message","登录成功！");
            mv.setViewName("redirect:/pages/index.html");
        }else {

            mv.addObject("message","用户名或密码错误！");

            mv.addObject("user", null);
        }
        return mv;
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(Model model, String  username, String password){
        User user = userService.isUser(username, password);

        if(user != null){
            model.addAttribute("user",user);
            return "redirect:index.html";
        }
        return "用户名或密码错误！";
    }


    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "hello world";
    }


}
