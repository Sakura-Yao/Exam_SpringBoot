package com.huade.controller;

import com.huade.pojo.ClassInfo;
import com.huade.pojo.View_ClassInfo;
import com.huade.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/classInfo")
public class ClassInfoController {
    @Autowired
    private ClassInfoService classInfoService;

    //ip:part/classInfo/addClassInfo
    @RequestMapping("/addClassInfo")
    @ResponseBody
    public int addClassInfo( @RequestParam("class_Id") String class_Id, @RequestParam("people_Num") String people_Num, @RequestParam("class_Col_Id") String class_Col_Id, @RequestParam("class_Spe_Id") String class_Spe_Id){
        String Id = UUID.randomUUID().toString().replace("-","");
        ClassInfo classInfo = new ClassInfo(Id,class_Id,people_Num,class_Col_Id,class_Spe_Id);
        return classInfoService.addClassInfo(classInfo);
    }

    @RequestMapping("/updateClassInfo")
    @ResponseBody
    public int updateClassInfo(@RequestParam("Id") String Id,@RequestParam("class_Id") String class_Id,@RequestParam("people_Num") String people_Num,@RequestParam("class_Col_Id") String class_Col_Id,@RequestParam("class_Spe_Id") String class_Spe_Id){
        ClassInfo classInfo = new ClassInfo(Id,class_Id,people_Num,class_Col_Id,class_Spe_Id);
        return classInfoService.updateClassInfo(classInfo);
    }

    @RequestMapping("/deleteClassInfo")
    @ResponseBody
    public int deleteClassInfo(@RequestParam("Id") String Id){
        return classInfoService.deleteClassInfo(Id);
    }

    @RequestMapping("/selectAllClassInfo")
    @ResponseBody
    public List<View_ClassInfo> selectAllClassInfo(){
        return classInfoService.selectAllClassInfo();
    }

    @RequestMapping("/selectClassInfo")
    @ResponseBody
    public List<ClassInfo> selectClassInfo(@RequestParam("Id")String Id,
                                @RequestParam("col_Id")String col_Id,
                                @RequestParam("spe_Id")String spe_Id,
                                @RequestParam("current")String current,
                                @RequestParam("length")String length){
        return classInfoService.selectClassInfo(Id,col_Id,spe_Id,Integer.parseInt(current),Integer.parseInt(length));
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
