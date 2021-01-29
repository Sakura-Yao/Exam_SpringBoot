package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huade.pojo.CollegeInfo;
import com.huade.service.CollegeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/collegeInfo")
public class CollegeInfoController {
    @Autowired
    private CollegeInfoService collegeInfoService;

    @RequestMapping("/addCollegeInfo")
    @ResponseBody
    public JSON addCollegeInfo (HttpSession session, @RequestParam("Id") String Id, @RequestParam("col_Name") String col_Name){
        JSONObject object = new JSONObject();
        CollegeInfo collegeInfo = new CollegeInfo(Id,col_Name);
        if(session.getAttribute("login_session") != null ){
            if(collegeInfoService.addCollegeInfo(collegeInfo) == 1){
                object.put("code",1);
                object.put("message","添加学院信息成功！");
            }
            else {
                object.put("code",-1);
                object.put("message","添加学院信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/deleteCollegeInfo")
    @ResponseBody
    public JSON deleteCollegeInfo(HttpSession session, @RequestParam("col_Id")String col_Id) {
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null ){
            if(collegeInfoService.deleteCollegeInfo(col_Id) == 1){
                object.put("code",1);
                object.put("message","删除学院信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","删除学院信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/updateCollegeInfo")
    @ResponseBody
    public JSON updateCollegeInfo(HttpSession session,@RequestParam("Id")String Id,@RequestParam("col_Name")String col_Name){
        JSONObject object = new JSONObject();
        CollegeInfo collegeInfo = new CollegeInfo(Id,col_Name);
        if(session.getAttribute("login_session") != null){
            if(collegeInfoService.updateCollegeInfo(collegeInfo) == 1){
                object.put("code",1);
                object.put("message","修改学院信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","修改学院信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectAllCollegeInfo")
    @ResponseBody
    public JSON SelectAllCollegeInfo(HttpSession session) {
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            if(collegeInfoService.selectAllCollegeInfo() !=null ){
                object.put("code",1);
                object.put("message","查询成功！");
                object.put("data",collegeInfoService.selectAllCollegeInfo());
            }
            else {
                object.put("code",0);
                object.put("message","查询不到任何学院信息！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("selectByCol_Id")
    @ResponseBody
    public JSON SelectBycol_Id(HttpSession session,@RequestParam("col_Id") String col_Id){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            if (collegeInfoService.selectCollegeInfo_col_Id(col_Id) != null){
                object.put("code",1);
                object.put("message","查询成功！");
                object.put("data",collegeInfoService.selectCollegeInfo_col_Id(col_Id));

            }
            else {
                object.put("code",0);
                object.put("message","未查询到学院信息！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

}
