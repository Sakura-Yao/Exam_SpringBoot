package com.huade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huade.Utils.UtilTools;
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
public class LoginController {

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/getSession")
    @ResponseBody
    public JSON getSession(HttpSession session, @RequestParam("key")String key){
        JSONObject object = new JSONObject();
        object.put("session_data",session.getAttribute(key));
        return object;
    }

    @RequestMapping("/UserLogin")
    @ResponseBody
    public JSON UserLogin(HttpSession session, @RequestParam("user_Id")String user_Id, @RequestParam("password")String password){
        JSONObject object = new JSONObject();
        if (userService.Login(user_Id, UtilTools.Encrypted_MD5(password)) != null){
            User user = userService.Login(user_Id,UtilTools.Encrypted_MD5(password));
            session.setAttribute("login_session",user);
            object.put("code",1);
            object.put("message","success");
            object.put("data",user);
        }else {
            object.put("code",0);
            object.put("message","登陆失败！");
        }
        return object;
    }


}
