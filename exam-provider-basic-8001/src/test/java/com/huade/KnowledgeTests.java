package com.huade;

import com.huade.service.KnowledgeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ExamProviderBasic.class)
public class KnowledgeTests {

    @Autowired
    private KnowledgeServiceImpl knowledgeService;

    @Test
    public void t1(){
        System.out.println(knowledgeService.selectAllKnowledge(0, 0).size());
    }
}
