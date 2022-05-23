package com.example.lookie.group.service;

import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.group.repository.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    private final QuestionAnswerRepository questionAnswerRepository;

    /**
     * QuestionAnswer 생성
     */
    @Transactional
    @Override
    public Long makeQuestionAnswer(Long id, Question question, String answer, String title) {
        QuestionAnswer questionAnswer = QuestionAnswer.createRequestQuestion(question, answer, title);

        return questionAnswerRepository.save(questionAnswer).getId();
    }

    /**
     * QuestionAnswer 조회
     */
    @Override
    public List<QuestionAnswer> checkQuestionAnswer(String ownerEmail){
        questionAnswerRepository.findByOwnerEmail(ownerEmail).orElseThrow(()-> {
            throw new IllegalArgumentException("회원님이 만든 그룹이 존재하지 않습니다.");
        });
        return questionAnswerRepository.findAll();
    }
}
