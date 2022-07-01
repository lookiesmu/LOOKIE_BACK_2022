package com.example.lookie.group.service;

import com.example.lookie.group.domain.Question;
import java.util.Optional;

public interface QuestionService {
    void addQuestion(String ownerEmail, String title);
    void deleteQuestion(Long questionId);
    void modifyQuestion(Long questionId, String changeTitle);
}
