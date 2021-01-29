package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huade.pojo.ClassInfo;
import com.huade.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/classInfo")
public class ClassInfoController {
    @Autowired
    private ClassInfoService classInfoService;

    //ip:part/classInfo/addClassInfo
    @RequestMapping("/addClassInfo")
    @ResponseBody
    public JSON addClassInfo(HttpSession session, @RequestParam("Id") String Id, @RequestParam("class_Id") String class_Id, @RequestParam("people_Num") String people_Num, @RequestParam("class_Col_Id") String class_Col_Id, @RequestParam("class_Spe_Id") String class_Spe_Id){
        JSONObject object = new JSONObject();
        ClassInfo classInfo = new ClassInfo(Id,class_Id,people_Num,class_Col_Id,class_Spe_Id);
        if(session.getAttribute("login_session") != null){
            if (classInfoService.addClassInfo(classInfo) == 1){
                object.put("code",1);
                object.put("message","添加班级信息成功！");
            }else {
                object.put("code",0);
                object.put("message","添加班级信息失败！");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/updateClassInfo")
    @ResponseBody
    public JSON updateClassInfo(HttpSession session,@RequestParam("Id") String Id,@RequestParam("class_Id") String class_Id,@RequestParam("people_Num") String people_Num,@RequestParam("class_Col_Id") String class_Col_Id,@RequestParam("class_Spe_Id") String class_Spe_Id){
        JSONObject object = new JSONObject();
        ClassInfo classInfo = new ClassInfo(Id,class_Id,people_Num,class_Col_Id,class_Spe_Id);
        if(session.getAttribute("login_session") != null){
            if(classInfoService.updateClassInfo(classInfo) == 1){
                object.put("code",1);
                object.put("message","修改班级信息成功！");
            }else{
                object.put("code",0);
                object.put("messgae","修改班级信息失败！");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/deleteClassInfo")
    @ResponseBody
    public JSON deleteClassInfo(HttpSession session, @RequestParam("Id") String Id){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            if(classInfoService.deleteClassInfo(Id) == 1){
                object.put("code",1);
                object.put("message","删除班级信息成功！");
            }else {
                object.put("code",0);
                object.put("message","删除班级信息失败！");
            }
        }else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectAllClassInfo")
    @ResponseBody
    public JSON selectAllClassInfo(HttpSession session){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            object.put("code",1);
            object.put("message","查询成功！");
            object.put("data",classInfoService.selectAllClassInfo());
        } else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/selectClassInfo")
    @ResponseBody
    public JSON selectClassInfo(HttpSession session,@RequestParam("Id")String Id,
                                @RequestParam("cou_Id")String cou_Id,
                                @RequestParam("spe_Id")String spe_Id,
                                @RequestParam("current")int current,
                                @RequestParam("length")int length){
        JSONObject object = new JSONObject();
        if(session.getAttribute("login_session") != null){
            object.put("code",1);
            object.put("message","查询成功！");
            object.put("data",classInfoService.selectClassInfo(Id,cou_Id,spe_Id,current,length));
        } else {
            object.put("code",-1);
            object.put("message","登陆状态失效！请重新登录！");
        }
        return object;
    }

//    @RequestMapping("/selectAllClassInfo")
//    @ResponseBody
//    public JSON selectAllClassInfo(HttpSession session,@Param("current") int current,@Param("length") int length){
//        JSONObject object = new JSONObject();
//        ObjectMapper mapper = new ObjectMapper();
//        if(session.getAttribute("login_session") != null){
//            if(classInfoService.selectAllClassInfo(current,length) != null){
//                object.put("code",1);
//                object.put("message","查询班级信息成功！");
//                object.put("data",classInfoService.selectAllClassInfo(current,length));
//
//
//            }else {
//                object.put("code",0);
//                object.put("message","未查询到班级信息！");
//
//            }
//        }else {
//            object.put("code",-1);
//            object.put("message","登录状态失效！请重新登录！");
//
//        }
//    }


    //需要修改
//    @RequestMapping("/selectClassInfo")
//    @ResponseBody
//    public JSON selectClassInfo(HttpSession session,
//                                  @Param("col_Id") String col_Id,
//                                  @Param("spe_Id") String spe_Id,
//                                  @Param("current") int current,
//                                  @Param("length") int length){
//        JSONObject object = new JSONObject();
//        ObjectMapper mapper = new ObjectMapper();
//        if(session.getAttribute("login_session") != null){
//            if(classInfoService.selectClassInfo(col_Id, spe_Id, current, length) != null){
//                object.put("code",1);
//                object.put("message","查询班级信息成功！");
//                object.put("data",classInfoService.selectClassInfo(col_Id, spe_Id, current, length));
//
//
//            }else {
//                object.put("code",0);
//                object.put("message","未查询到班级信息！");
//
//            }
//        }else {
//            object.put("code",-1);
//            object.put("message","登陆状态失效！请重新登录！");
//
//        }
//    }
//

}
