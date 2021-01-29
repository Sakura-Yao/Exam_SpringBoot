package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huade.pojo.ClassCourseInfo;
import com.huade.pojo.User;
import com.huade.service.ClassCourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/classCourseInfo")
public class ClassCourseInfoController {
    @Autowired
    private ClassCourseInfoService classCourseInfoService;

    @RequestMapping("/addClassCourseInfo")
    @ResponseBody
    public JSON addClassCourseInfo(HttpSession session, @RequestParam("classes_Id") String classes_Id, @RequestParam("user_Id") String user_Id, @RequestParam("cou_Id") String cou_Id){
        JSONObject object = new JSONObject();
        ClassCourseInfo classCourseInfo = new ClassCourseInfo(classes_Id, user_Id, cou_Id);
        if(session.getAttribute("login_session") != null){
            if (classCourseInfoService.addClassCourseInfo(classCourseInfo) == 1){
                object.put("code",1);
                object.put("message","添加成功");
            }else {
                object.put("code",0);
                object.put("message","添加失败");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/deleteClassCourseInfo")
    @ResponseBody
    public JSON deleteClassCourseInfo(HttpSession session,@RequestParam("classes_Id") String classes_Id,@RequestParam("user_Id") String user_Id,@RequestParam("cou_Id") String cou_Id){
        JSONObject object = new JSONObject();
        ClassCourseInfo classCourseInfo = new ClassCourseInfo(classes_Id, user_Id, cou_Id);
        if(session.getAttribute("login_session") != null){
            if (classCourseInfoService.deleteClassCourseInfo(classCourseInfo) == 1){
                object.put("code",1);
                object.put("message","删除成功");
            }else {
                object.put("code",0);
                object.put("message","删除失败");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }
    @RequestMapping("/updateClassCourseInfo")
    @ResponseBody
    public JSON updateClassCourseInfo(HttpSession session,@RequestParam("new_classes_Id") String new_classes_Id,@RequestParam("new_user_Id") String new_user_Id,@RequestParam("new_cou_Id") String new_cou_Id,@RequestParam("old_classes_Id") String old_classes_Id,@RequestParam("old_user_Id") String old_user_Id,@RequestParam("old_cou_Id") String old_cou_Id){
        JSONObject object = new JSONObject();
        ClassCourseInfo new_classCourseInfo = new ClassCourseInfo(new_classes_Id, new_user_Id, new_cou_Id);
        ClassCourseInfo old_classCourseInfo = new ClassCourseInfo(old_classes_Id, old_user_Id, old_cou_Id);
        if (session.getAttribute("login_session") != null){
            if (classCourseInfoService.updateClassCourseInfo(new_classCourseInfo,old_classCourseInfo) == 1){
                object.put("code",1);
                object.put("message","修改成功");
            }else {
                object.put("code",0);
                object.put("message","修改失败");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }
    @RequestMapping("/selectAllClassCourseInfo")
    @ResponseBody
    public JSON selectAllClassCourseInfo(HttpSession session, @RequestParam("current") int current, @RequestParam("length") int length){
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null){
            if (classCourseInfoService.selectAllClassCourseInfo(current, length) != null){
                object.put("code",1);
                object.put("message","查询成功");
                object.put("data",classCourseInfoService.selectAllClassCourseInfo(current, length));
            }else {
                object.put("code",0);
                object.put("message","未查询到任何信息");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }
    @RequestMapping("/selectClassCourseInfo")
    @ResponseBody
    public JSON selectClassCourseInfo(HttpSession session,@RequestParam("class_Id") String[] class_Id,@RequestParam("user_Id") String user_Id,@RequestParam("cou_Id") String cou_Id,@RequestParam("current") int current,@RequestParam("length") int length){
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null){
            User user = (User) session.getAttribute("login_session");
            if (classCourseInfoService.selectClassCourseInfo(class_Id,user.getUser_Id(),cou_Id,current,length) != null){
                object.put("code",1);
                object.put("message","查询成功");
                object.put("data",classCourseInfoService.selectClassCourseInfo(class_Id,user.getUser_Id(),cou_Id,current,length));
            }else {
                object.put("code",0);
                object.put("message","未查询到任何信息");
            }
        }else {
            object.put("code", -1);
            object.put("message", "登陆状态失效！请重新登录！");
        }
        return object;
    }



}
