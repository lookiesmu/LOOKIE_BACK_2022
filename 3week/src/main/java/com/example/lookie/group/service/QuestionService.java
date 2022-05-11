package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.group.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {
    private QuestionRepository questionRepository;
    private GroupRepository groupRepository;

    public Long joinQuestion(Group group, String title) {
        validateGroup(group);

        Question question = Question.createQuestion(group, title);
        questionRepository.save(question);
        return question.getId();
    }

    private void validateGroup(Group group) {
        List<Group> findGroups = groupRepository.findByName(group.getName());
        if (findGroups.isEmpty()) {
            throw new IllegalStateException("동아리가 존재하지 않습니다.");
        }
    }

    //삭제
    @Transactional
    public void deleteQuestion(Question question) {

        questionRepository.deleteQuestion(question.getId());
    }

    //title 수정
    @Transactional
    public void modifyQuestion(Long questionId, String title) {
        Question question = questionRepository.findOne(questionId);
        question.modifyTitle(title);
    }
}
