package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huade.pojo.User;
import com.huade.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/SelectAllUser")
    @ResponseBody
    public JSON selectAllUser(HttpSession session) {
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            if (userService.selectAllUser() !=null){
                object.put("code", 1);
                object.put("message", "success");
                object.put("data",userService.selectAllUser());
            }else {
                object.put("code", 0);
                object.put("message", "未查询到该信息");
            }
        }else {
            object.put("code",-1);
            object.put("message","登录状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public JSON addUser (HttpSession session, @RequestParam("user_Id") String user_Id, @RequestParam("password") String password, @RequestParam("user_Name") String user_Name, @RequestParam("user_Type") String user_Type, @RequestParam("user_Sex")String user_Sex, @RequestParam("user_Mobile") String user_Mobile) {
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            User user = new User(user_Id,password,user_Name,user_Type,user_Sex,user_Mobile);
            if (userService.addUser(user) == 1) {
                object.put("code",1);
                object.put("message","success");
            }
            else {
                object.put("code",0);
                object.put("message","添加学生信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/DeleteUser")
    @ResponseBody
    public JSON DeleteUser (HttpSession session,@RequestParam("user_id")String user_id){
        JSONObject object = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        if (session.getAttribute("login_session") != null) {
            if (userService.deleteUser(user_id) == 1) {
                object.put("code",1);
                object.put("message","success");
            }
            else {
                object.put("code",0);
                object.put("message","删除用户失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/UpdateUser")
    @ResponseBody
    public JSON UpdateUser (HttpSession session,@RequestParam("user_id")String user_id,
                              @RequestParam("password")String password,
                              @RequestParam("user_Name")String user_Name,
                              @RequestParam("user_Type")String user_Type,
                              @RequestParam("user_Sex")String user_Sex,
                              @RequestParam("user_Mobile")String user_Mobile) {
        JSONObject object = new JSONObject();
        User user = new User(user_id, password, user_Name, user_Type, user_Sex, user_Mobile);
        if (session.getAttribute("login_session") != null) {
            if (userService.updateUser(user) == 1) {
                object.put("code",1);
                object.put("message","success");
            }
            else {
                object.put("code",0);
                object.put("message","修改用户失败！请重试！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }


}
