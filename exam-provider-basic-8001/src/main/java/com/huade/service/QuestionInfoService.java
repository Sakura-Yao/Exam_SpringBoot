package com.huade.service;

import com.huade.pojo.QuestionInfo;

import java.util.List;

public interface QuestionInfoService {

    int addQuestionInfo(QuestionInfo questionInfo);

    int deleteQuestionInfo(String question_Id);

    int updateQuestionInfo(QuestionInfo questionInfo);

    List<QuestionInfo> selectQuestionInfo(String question_Id, String cou_Id, String type_Id, String subject, String degree, String kwl_Id,
                                          int current, int length);

}
