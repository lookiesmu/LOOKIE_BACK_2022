package com.example.lookie.group.service;

import com.example.lookie.group.domain.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    void createQuestionAnswer(Long questionId, String answer, String title);
    List<QuestionAnswer> findAllQuestionAnswer(String ownerEmail);
}
