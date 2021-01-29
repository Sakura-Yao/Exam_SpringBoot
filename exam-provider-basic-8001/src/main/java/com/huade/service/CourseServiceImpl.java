package com.huade.service;

import com.huade.mapper.CourseMapper;
import com.huade.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public int addCourseInfo(Course course) {
        return courseMapper.addCourseInfo(course);
    }

    @Override
    public int batchAddCourseInfo(List<Map<String, Object>> courseInfoList) throws Exception {
        return courseMapper.batchAddCourseInfo(courseInfoList);
    }

    @Override
    public int deleteCourseInfo(String Id) {
        return courseMapper.deleteCourseInfo(Id);
    }

    @Override
    public int updateCourseInfo(Course course) {
        return courseMapper.updateCourseInfo(course);
    }

    @Override
    public List<Course> selectAllCourseInfo(int current, int length) {
        return courseMapper.selectAllCourseInfo(current,length);
    }

    @Override
    public List<Course> selectCourseInfo(String spe_Id,int current, int length) {
        return courseMapper.selectCourseInfo(spe_Id,current,length);
    }
}
