package com.huade.controller;

import com.huade.Utils.UtilTools;
import com.huade.pojo.Course;
import com.huade.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/addCourseInfo")
    @ResponseBody
    public int addCourseInfo(@RequestParam("cou_Name") String cou_Name, @RequestParam("spe_Id") String spe_Id){
        Course course = new Course(UtilTools.RandomCourseId(),cou_Name,spe_Id);
        return courseService.addCourseInfo(course);
    }

    @RequestMapping("/deleteCourseInfo")
    @ResponseBody
    public int deleteCourseInfo(@RequestParam("cou_Id") String cou_Id) {
        return courseService.deleteCourseInfo(cou_Id);
    }

    @RequestMapping("/updateCourseInfo")
    @ResponseBody
    public int updateCourseInfo(@RequestParam("Id") String Id,@RequestParam("cou_Name") String cou_Name,@RequestParam("spe_Id") String spe_Id){
        Course course = new Course(Id,cou_Name,spe_Id);
        return courseService.updateCourseInfo(course);
    }

    @RequestMapping("/selectAllCourseInfo")
    @ResponseBody
    public List<Course> selectAllCourseInfo(@RequestParam("current") int current, @RequestParam("length") int length){
        return courseService.selectAllCourseInfo(current, length);
    }

    @RequestMapping("/selectCourseInfo")
    @ResponseBody
    public List<Course> selectCourseInfo(@RequestParam("spe_Id") String spe_Id,@RequestParam("current") int current,@RequestParam("length") int length){
        return courseService.selectCourseInfo(spe_Id, current, length);
    }




}
