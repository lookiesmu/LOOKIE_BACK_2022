package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.group.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final GroupRepository groupRepository;

    @Override
    public void createQuestion(String ownerEmail, String title) {
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 이메일로 생성된 동아리가 없습니다.");
        });

        Question question = Question.createQuestion(group, title);
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public void modifyQuestion(Long questionId, String modifyTitle) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 질문이 존재하지 않습니다.");
        });

        question.modifyTitle(modifyTitle);
    }
}