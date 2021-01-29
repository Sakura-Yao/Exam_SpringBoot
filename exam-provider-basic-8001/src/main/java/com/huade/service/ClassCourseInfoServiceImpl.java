package com.huade.service;

import com.huade.mapper.ClassCourseInfoMapper;
import com.huade.pojo.ClassCourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassCourseInfoServiceImpl implements ClassCourseInfoService {

    @Autowired
    private ClassCourseInfoMapper classCourseInfoMapper;

    public void setClassCourseInfoMapper(ClassCourseInfoMapper classCourseInfoMapper) {
        this.classCourseInfoMapper = classCourseInfoMapper;
    }

    @Override
    public int addClassCourseInfo(ClassCourseInfo classCourseInfo) {
        return classCourseInfoMapper.addClassCourseInfo(classCourseInfo);
    }

    @Override
    public int deleteClassCourseInfo(ClassCourseInfo classCourseInfo) {
        return classCourseInfoMapper.deleteClassCourseInfo(classCourseInfo);
    }

    @Override
    public int updateClassCourseInfo(ClassCourseInfo new_ClassCourseInfo, ClassCourseInfo old_ClassCourseInfo) {
        return classCourseInfoMapper.updateClassCourseInfo(new_ClassCourseInfo,old_ClassCourseInfo);
    }

    @Override
    public List<ClassCourseInfoMapper> selectAllClassCourseInfo(int current, int length) {
        return classCourseInfoMapper.selectAllClassCourseInfo(current, length);
    }

    @Override
    public List<ClassCourseInfoMapper> selectClassCourseInfo(String[] class_Id, String user_Id, String cou_Id, int current, int length) {
        return classCourseInfoMapper.selectClassCourseInfo(class_Id, user_Id, cou_Id, current, length);
    }
}
