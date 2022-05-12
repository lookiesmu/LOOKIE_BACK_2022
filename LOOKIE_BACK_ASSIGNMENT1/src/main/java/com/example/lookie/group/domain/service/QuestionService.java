package com.example.lookie.group.domain.service;

import com.example.lookie.group.domain.Repository.GroupRepository;
import com.example.lookie.group.domain.Repository.QuestionRepository;
import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.group.domain.domain.Question;
import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {
    private final GroupRepository groupRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public Long createQuestion(Group group, String title){
        validateDuplicateGroup(title);
        Question question = Question.createQuestion(group,title);
        questionRepository.save(question);
        return question.getId();
    }

    private void validateDuplicateGroup(String name){
        List<Group> groups = groupRepository.findByName(name);
        if (groups.isEmpty()) {
            throw new IllegalStateException("동아리가 존재하지 않습니다.");

        }
    }
    //question 수정
    @Transactional
    public void ModifyTitle(Long id, String title){
        Question question = questionRepository.findOne(id);
        question.ModifyTitle(title);
    }
    //question 삭제
    @Transactional
    public void DeleteQuestion(Long id){
        questionRepository.remove(id);
    }
}
