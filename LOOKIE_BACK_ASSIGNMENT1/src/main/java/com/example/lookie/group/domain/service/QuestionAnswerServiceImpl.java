package com.example.lookie.group.domain.service;

import com.example.lookie.group.domain.Repository.GroupRepository1;
import com.example.lookie.group.domain.Repository.QuestionAnswerRepository;
import com.example.lookie.group.domain.Repository.QuestionRepository1;
import com.example.lookie.group.domain.domain.Question;
import com.example.lookie.group.domain.domain.QuestionAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    private final QuestionRepository1 questionRepository1;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final GroupRepository1 groupRepository1;


    @Transactional
    @Override
    public Long createQuestionAnswer(Long Questionid, Question question, String answer, String title){
        questionRepository1.findById(Questionid).orElseThrow(() -> {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        });

        QuestionAnswer questionAnswer = QuestionAnswer.createRequestQuestion(question, answer, title);

        return questionAnswerRepository.save(questionAnswer).getId();
    }

    @Override
    public List<QuestionAnswer> findQuestionAnswer(String ownerEmail){
        groupRepository1.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("소유한 그룹이 없습니다.");
        });

        return questionAnswerRepository.findAll();

    }
}
