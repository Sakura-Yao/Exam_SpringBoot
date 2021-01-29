package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huade.pojo.Teacher_Basic;
import com.huade.pojo.User;
import com.huade.service.TeacherBasicServiceImpl;
import com.huade.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/Teacher_basic")
public class Teacher_basicController {
    @Autowired
    private TeacherBasicServiceImpl teacherBasicService;

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/addTeacherBasicInfo")
    @ResponseBody
    public JSON addTeacher (HttpSession session, @RequestParam("user_Id") String user_Id,
                            @RequestParam("password") String password,
                            @RequestParam("user_Name") String user_Name,
                            @RequestParam("user_Type") String user_Type,
                            @RequestParam("user_Sex")String user_Sex,
                            @RequestParam("user_Mobile") String user_Mobile,
                            @RequestParam("college_Id") String college_Id,
                            @RequestParam("specialty_Id")String specialty_Id) {
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            User user = new User(user_Id,password,user_Name,user_Type,user_Sex,user_Mobile);
            Teacher_Basic teacher_Basic = new Teacher_Basic(user_Id,college_Id,specialty_Id);
            if (teacherBasicService.addTeacherBasicInfo(teacher_Basic)==1 && userService.addUser(user) == 1){
                object.put("code",1);
                object.put("message","添加教师基础信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","添加教师基础信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登陆！");
        }
        return object;
    }

    @RequestMapping("/deleteTeacherBasicInfo")
    @ResponseBody
    public JSON deleteTeacherBasicInfo(HttpSession session, @RequestParam("user_Id")String user_Id) {
        JSONObject object = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        if (session.getAttribute("login_session") != null) {
            if (teacherBasicService.deleteTeacherBasicInfo(user_Id)==1 && userService.deleteUser(user_Id) == 1) {
                object.put("code",1);
                object.put("message","删除教师基础信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","删除教师基础信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/updateTeacherBasicInfo")
    @ResponseBody
    public JSON updateTeacherBasicInfo (HttpSession session,
                                          @RequestParam("user_Id") String user_Id,
                                          @RequestParam("college_Id") String college_Id,
                                          @RequestParam("specialty_Id")String specialty_Id){
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            Teacher_Basic teacher_Basic = new Teacher_Basic(user_Id,college_Id,specialty_Id);
            if (teacherBasicService.updateTeacherBasicInfo(teacher_Basic)==1) {
                object.put("code",1);
                object.put("message","修改教师基础信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","修改教师基础信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectTeacher")
    @ResponseBody
    public JSON selectTeacher (HttpSession session,@RequestParam("user_Id")String user_Id,
                                 @RequestParam("college_Id")String college_Id,
                                 @RequestParam("specialty_Id")String specialty_Id,
                                 @RequestParam("current")int current,
                                 @RequestParam("length")int length) {
        JSONObject object = new JSONObject();
        ArrayList<Teacher_Basic> teacher_Basic = new ArrayList<Teacher_Basic>();
        if (session.getAttribute("login_session") != null) {
            if (teacherBasicService.selectTeacherBasic(user_Id,college_Id,specialty_Id,current,length).size()!=0) {
                object.put("code",1);
                object.put("message","查询教师基础信息成功！");
                object.put("data",teacherBasicService.selectTeacherBasic(user_Id,college_Id,specialty_Id,current,length));
            }
            else {
                object.put("code",0);
                object.put("message","查询教师基础信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }



}
