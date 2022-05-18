package com.example.lookie.group.domain.service;


public interface QuestionService {

    void createNewQuestion (String newQuestion, String groupName);
    void deleteQuestion (Long questionId, String userEmail, String ownerEmail);
    void changeQuestionTitle (Long questionId, String changedQuestion, String userEmail);

}