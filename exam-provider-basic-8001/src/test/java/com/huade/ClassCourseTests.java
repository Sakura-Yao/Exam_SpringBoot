package com.huade;

import com.huade.service.ClassCourseInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ExamProviderBasic.class)
public class ClassCourseTests {

    @Autowired
    private ClassCourseInfoServiceImpl service;

    @Test
    public void t1(){
        System.out.println(service.selectAllClassCourseInfo(0,0).size());
    }

    @Test
    void selectAllTeachCourse() {
    }
}
