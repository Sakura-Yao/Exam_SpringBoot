package com.huade.service;

import com.huade.pojo.ClassCourseInfo;
import com.huade.pojo.View_Teacher_Class_Info;

import java.util.List;

public interface ClassCourseInfoService {

    int addClassCourseInfo(ClassCourseInfo classCourseInfo);

    int deleteClassCourseInfo(ClassCourseInfo classCourseInfo);

    int updateClassCourseInfo (ClassCourseInfo new_ClassCourseInfo,
                               ClassCourseInfo old_ClassCourseInfo);

    List<View_Teacher_Class_Info> selectAllClassCourseInfo(int current, int length);

    List<View_Teacher_Class_Info> selectClassCourseInfo(String[] class_Id, String user_Id, String cou_Id,
                                                        int current, int length);


}
