package com.huade.mapper;

import com.huade.pojo.ClassCourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassCourseInfoMapper {

    int addClassCourseInfo(ClassCourseInfo classCourseInfo);

    int deleteClassCourseInfo(ClassCourseInfo classCourseInfo);

    int updateClassCourseInfo (@Param("new_ClassCourseInfo") ClassCourseInfo new_ClassCourseInfo,
                               @Param("old_ClassCourseInfo") ClassCourseInfo old_ClassCourseInfo);

    List<ClassCourseInfoMapper> selectAllClassCourseInfo(@Param("current")int current, @Param("length") int length);

    List<ClassCourseInfoMapper> selectClassCourseInfo(@Param("class_Id") String[] class_Id,
                                                      @Param("user_Id")String user_Id,
                                                      @Param("cou_Id")String cou_Id,
                                                      @Param("current")int current, @Param("length") int length);

}
