package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huade.pojo.Specialty;
import com.huade.service.SpecialtyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Specialty")
public class SpecialtyController {
    @Autowired
    private SpecialtyInfoService specialtyInfoService;

    @RequestMapping("/addSpecialtyInfo")
    @ResponseBody
    public JSON addSpecialtyInfo(HttpSession session, @RequestParam("Id") String Id, @RequestParam("spe_Name") String spe_Name, @RequestParam("cou_Id") String cou_Id) {
        JSONObject object = new JSONObject();
        Specialty specialty = new Specialty(Id,spe_Name,cou_Id);
        if(session.getAttribute("login_session") != null){
            if(specialtyInfoService.addSpecialtyInfo(specialty) == 1){
                object.put("code",1);
                object.put("message","添加专业信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","添加专业信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/deleteSpecialtyInfo")
    @ResponseBody
    public JSON deleteSpecialtyInfo(HttpSession session, @RequestParam("Id") String Id){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            if(specialtyInfoService.deleteSpecialtyInfo(Id) == 1){
                object.put("code",1);
                object.put("message","删除专业信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","删除专业信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/updateSpecialtyInfo")
    @ResponseBody
    public JSON updateSpecialtyInfo(HttpSession session,@RequestParam("Id") String Id,@RequestParam("spe_Name") String spe_Name,@RequestParam("col_Id") String col_Id) {
        JSONObject object = new JSONObject();
        Specialty specialty = new Specialty(Id,spe_Name,col_Id);
        if(session.getAttribute("login_session") != null){
            if (specialtyInfoService.updateSpecialtyInfo(specialty) == 1){
                object.put("code",1);
                object.put("message","修改专业信息成功！");
            }
            else {
                object.put("code",0);
                object.put("message","修改专业信息失败！");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectAllSpecialty")
    @ResponseBody
    public JSON selectAllSpecialty(HttpSession session){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            if(specialtyInfoService.selectAllSpecialty() != null){
                object.put("code",1);
                object.put("message","查询专业信息成功！");
                object.put("data",specialtyInfoService.selectAllSpecialty());

            }else {
                object.put("code",0);
                object.put("message","查询不到任何专业信息！");
            }

        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectCollegeInfo_col_Id")
    @ResponseBody
    public JSON selectCollegeInfo_col_Id(HttpSession session,@RequestParam("col_Id") String col_Id){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            if(specialtyInfoService.selectSpecialty_col_Id(col_Id) != null){
                object.put("code",1);
                object.put("message","查询专业信息成功！");
                object.put("data",specialtyInfoService.selectSpecialty_col_Id(col_Id));
            }else {
                object.put("code",0);
                object.put("message","查询不到任何专业信息");
            }
        }
        else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

}
