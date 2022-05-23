package com.example.lookie.question.service;

public interface QuestionService {
    void createQuestion(String ownerEmail, String title);
    void deleteQuestion(Long questionId);
    void modifyQuestion(Long questionId, String modifyTitle);
}