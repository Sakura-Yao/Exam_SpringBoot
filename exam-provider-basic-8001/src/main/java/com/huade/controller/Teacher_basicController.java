package com.huade.controller;

import com.huade.pojo.Teacher_Basic;
import com.huade.pojo.User;
import com.huade.service.TeacherBasicServiceImpl;
import com.huade.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Teacher_basic")
public class Teacher_basicController {
    @Autowired
    private TeacherBasicServiceImpl teacherBasicService;

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/addTeacherBasicInfo")
    @ResponseBody
    public int addTeacher (@RequestParam("user_Id") String user_Id,
                            @RequestParam("password") String password,
                            @RequestParam("user_Name") String user_Name,
                            @RequestParam("user_Type") String user_Type,
                            @RequestParam("user_Sex")String user_Sex,
                            @RequestParam("user_Mobile") String user_Mobile,
                            @RequestParam("college_Id") String college_Id,
                            @RequestParam("specialty_Id")String specialty_Id) {
        User user = new User(user_Id,password,user_Name,user_Type,user_Sex,user_Mobile);
        Teacher_Basic teacher_Basic = new Teacher_Basic(user_Id,college_Id,specialty_Id);
        if (teacherBasicService.addTeacherBasicInfo(teacher_Basic)==1 && userService.addUser(user) == 1){
            return 1;
        }
        else {
            return 0;
        }
    }

    @RequestMapping("/deleteTeacherBasicInfo")
    @ResponseBody
    public int deleteTeacherBasicInfo(@RequestParam("user_Id")String user_Id) {
        if (teacherBasicService.deleteTeacherBasicInfo(user_Id)==1 && userService.deleteUser(user_Id) == 1) {
            return 1;
        }
        else {
            return 0;
        }
    }

    @RequestMapping("/updateTeacherBasicInfo")
    @ResponseBody
    public int updateTeacherBasicInfo (@RequestParam("user_Id") String user_Id,
                                       @RequestParam("college_Id") String college_Id,
                                       @RequestParam("specialty_Id")String specialty_Id){

        Teacher_Basic teacher_Basic = new Teacher_Basic(user_Id,college_Id,specialty_Id);
        return teacherBasicService.updateTeacherBasicInfo(teacher_Basic);
    }

    @RequestMapping("/selectTeacher")
    @ResponseBody
    public List<Teacher_Basic> selectTeacher (@RequestParam("user_Id")String user_Id,
                                              @RequestParam("college_Id")String college_Id,
                                              @RequestParam("specialty_Id")String specialty_Id,
                                              @RequestParam("current")int current,
                                              @RequestParam("length")int length) {
        return teacherBasicService.selectTeacherBasic(user_Id,college_Id,specialty_Id,current,length);
    }



}
