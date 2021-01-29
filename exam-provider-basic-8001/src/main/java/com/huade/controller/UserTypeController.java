package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huade.pojo.User_Type;
import com.huade.service.UserTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/UserType")
public class UserTypeController {
    @Autowired
    private UserTypeServiceImpl userTypeService;

    @RequestMapping("/SelectUserType_Id")
    @ResponseBody
    public JSON selectUserType_Id(@RequestParam("Id")String Id, HttpSession session){
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            if (userTypeService.selectUserType_Id(Id) !=null){
                object.put("code", 1);
                object.put("message", "success");
                object.put("data",userTypeService.selectUserType_Id(Id));
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

    @RequestMapping("/SelectAllUserType")
    @ResponseBody
    public JSON selectAllUserType(HttpSession session){
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            if (userTypeService.selectAllUserType() !=null){
                object.put("code", 1);
                object.put("message", "success");
                object.put("data",userTypeService.selectAllUserType());
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

//    @RequestMapping("/AddUserType")
//    @ResponseBody
//    public String addUserType(HttpSession session)throws JsonProcessingException{
//        JSONObject object = new JSONObject();
//        ObjectMapper mapper = new ObjectMapper();
//        if (session.getAttribute("login_session") != null) {
//                object.put("code", 1);
//                object.put("message", "success");
//                object.put("data",session.getAttribute("login_session"));
//                return mapper.writeValueAsString(object);
//        }else {
//            object.put("code",-1);
//            object.put("message","登录状态失效！请重新登录！");
//            return mapper.writeValueAsString(object);
//        }
//    }

    @RequestMapping("/AddUserType")
    @ResponseBody
    public JSON addUserType(HttpSession session,@RequestParam("user_Type")String user_Type){
        JSONObject object = new JSONObject();
        User_Type user_type = new User_Type(UUID.randomUUID().toString().replace("-",""),user_Type);
        if (session.getAttribute("login_session") != null) {
            if (userTypeService.addUserType(user_type) == 1){
                object.put("code", 1);
                object.put("message", "用户类型添加成功");
            }else {
                object.put("code", 0);
                object.put("message", "用户类型添加失败");
            }
        }else {
            object.put("code",-1);
            object.put("message","登录状态失效！请重新登录！");
        }
        return object;
    }

    @RequestMapping("/DeleteUserType")
    @ResponseBody
    public JSON deleteUserType(HttpSession session,@RequestParam("Id")String Id){
        JSONObject object = new JSONObject();
        if (session.getAttribute("login_session") != null) {
            if (userTypeService.deleteUserType(Id) == 1){
                object.put("code", 1);
                object.put("message", "用户类型删除成功");
            }else {
                object.put("code", 0);
                object.put("message", "用户类型删除失败");
            }
        }else {
            object.put("code",-1);
            object.put("message","登录状态失效！请重新登录！");
        }
        return object;
    }

}
