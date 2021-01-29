package com.huade;

import com.huade.service.ClassInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ExamProviderBasic.class)
public class ClassInfoTests {

    @Autowired
    private ClassInfoServiceImpl classInfoService;

    @Test
    public void t1(){
        System.out.println(classInfoService.selectAllClassInfo().size());
    }

}
