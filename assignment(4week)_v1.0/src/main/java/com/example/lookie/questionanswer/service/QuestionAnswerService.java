package com.example.lookie.questionanswer.service;

import com.example.lookie.questionanswer.domain.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {

    void createQuestionAnswer(Long questionId, String answer, String title);
    List<QuestionAnswer> findAllQuestionAnswer(Long groupRequestId);
}
