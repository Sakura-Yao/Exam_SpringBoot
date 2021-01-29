package com.huade.service;

import com.huade.mapper.QuestionInfoMapper;
import com.huade.pojo.QuestionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionInfoServiceImpl implements QuestionInfoMapper {

    @Autowired
    private QuestionInfoMapper questionInfoMapper;

    public int addQuestionInfo(QuestionInfo questionInfo) {
        return questionInfoMapper.addQuestionInfo(questionInfo);
    }

    public int deleteQuestionInfo(String question_Id) {
        return questionInfoMapper.deleteQuestionInfo(question_Id);
    }

    public int updateQuestionInfo(QuestionInfo questionInfo) {
        return questionInfoMapper.updateQuestionInfo(questionInfo);
    }

    public List<QuestionInfo> selectQuestionInfo(String question_Id, String cou_Id, String type_Id, String subject, String degree, String kwl_Id, int current, int length) {
        return questionInfoMapper.selectQuestionInfo(question_Id, cou_Id, type_Id, subject, degree, kwl_Id, current, length);
    }
}
