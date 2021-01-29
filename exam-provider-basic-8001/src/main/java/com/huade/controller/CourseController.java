package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huade.Utils.UtilTools;
import com.huade.pojo.Course;
import com.huade.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/addCourseInfo")
    @ResponseBody
    public JSON addCourseInfo(HttpSession session, @RequestParam("cou_Name") String cou_Name, @RequestParam("spe_Id") String spe_Id){
        JSONObject object = new JSONObject();
        Course course = new Course(UtilTools.RandomCourseId(),cou_Name,spe_Id);
        if(session.getAttribute("login_session") != null){
            if(courseService.addCourseInfo(course) == 1){
                object.put("code",1);
                object.put("message","添加课程成功！");
            }else {
                object.put("code",0);
                object.put("message","添加课程失败！");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/deleteCourseInfo")
    @ResponseBody
    public JSON deleteCourseInfo(HttpSession session, @RequestParam("cou_Id") String cou_Id) {
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null){
            if (courseService.deleteCourseInfo(cou_Id) == 1){
                object.put("code",1);
                object.put("message","删除课程成功！");
            }else {
                object.put("code",0);
                object.put("message","删除课程失败！");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/updateCourseInfo")
    @ResponseBody
    public JSON updateCourseInfo(HttpSession session,@RequestParam("Id") String Id,@RequestParam("cou_Name") String cou_Name,@RequestParam("spe_Id") String spe_Id){
        JSONObject object = new JSONObject();
        Course course = new Course(Id,cou_Name,spe_Id);
        if (session.getAttribute("login_session") != null) {
            if (courseService.updateCourseInfo(course) == 1) {
                object.put("code", 1);
                object.put("message", "修改课程成功！");
            } else {
                object.put("code", 0);
                object.put("message", "修改课程失败！");
            }
        } else {
            object.put("code", -1);
            object.put("message", "登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectAllCourseInfo")
    @ResponseBody
    public JSON selectAllCourseInfo(HttpSession session,@RequestParam("current") int current,@RequestParam("length") int length){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            if (courseService.selectAllCourseInfo(current, length) != null){
                object.put("code", 1);
                object.put("message", "查询课程成功！");
                object.put("data",courseService.selectAllCourseInfo(current, length));
            } else {
                object.put("code", 0);
                object.put("message", "未查询到任何课程！");
            }
        } else {
            object.put("code", -1);
            object.put("message", "登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectCourseInfo")
    @ResponseBody
    public JSON selectCourseInfo(HttpSession session,@RequestParam("spe_Id") String spe_Id,@RequestParam("current") int current,@RequestParam("length") int length){
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            if (courseService.selectCourseInfo(spe_Id, current, length) != null) {
                object.put("code", 1);
                object.put("message", "查询课程成功！");
                object.put("data",courseService.selectCourseInfo(spe_Id, current, length));
            } else {
                object.put("code", 0);
                object.put("message", "未查询到任何课程！");
            }
        } else {
            object.put("code", -1);
            object.put("message", "登陆状态失效！请重新登录！");
        }
        return object;
    }




}
