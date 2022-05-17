package com.example.lookie.group.service;

import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    Long createQuestionAnswer(Long id, Question question, String answer, String title);
    List<QuestionAnswer> checkQuestionAnswer(String ownerEmail);
}