package com.example.lookie.group.domain.service;

import com.example.lookie.group.domain.Repository.GroupRepository1;
import com.example.lookie.group.domain.Repository.QuestionRepository1;
import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.group.domain.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService1Impl implements QuestionService1{
    private final QuestionRepository1 questionRepository1;
    private final GroupRepository1 groupRepository1;

    @Override
    public void addQuestion(String OwnerEmail, String title){
        Group group = groupRepository1.findByOwnerEmail(OwnerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹을 만드시지 않았습니다.");
        });

        Question question = Question.createQuestion(group, title);
        questionRepository1.save(question);
    }

    @Override
    public void deleteQuestion(Long questionid){
        questionRepository1.deleteById(questionid);
    }

    @Override
    public void modifyQuestionTitle(Long QuestionId, String title){
        Question question = questionRepository1.findById(QuestionId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        });

        question.ModifyTitle(title);
    }


}
