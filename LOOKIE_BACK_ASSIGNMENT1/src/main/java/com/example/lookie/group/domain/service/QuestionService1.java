package com.example.lookie.group.domain.service;

public interface QuestionService1 {
    void addQuestion(String OwnerEmail, String title);
    void deleteQuestion(Long questionid);
    void modifyQuestionTitle(Long QuestionId, String title);
}
