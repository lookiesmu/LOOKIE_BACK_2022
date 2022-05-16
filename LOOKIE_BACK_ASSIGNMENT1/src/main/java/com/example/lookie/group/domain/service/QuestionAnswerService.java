package com.example.lookie.group.domain.service;

import com.example.lookie.group.domain.domain.Question;
import com.example.lookie.group.domain.domain.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    List<QuestionAnswer> findQuestionAnswer(String ownerEmail);
    Long createQuestionAnswer(Long Questionid, Question question, String answer, String title);
}
