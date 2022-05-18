package com.example.lookie.group.service;

import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.repository.QuestionAnswerRepository;
import com.example.lookie.group.repository.QuestionRepository;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    private final QuestionRepository questionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final GroupRequestRepository groupRequestRepository;

    @Transactional
    @Override
    public void createQuestionAnswer(Long questionId, String answer, String title) {
        Question question =questionRepository.findById(questionId).orElseThrow(()->{
            throw new IllegalArgumentException("질문이 없습니다.");
        });
        QuestionAnswer questionAnswer = QuestionAnswer.createRequestQuestion(question,answer,title);
        questionAnswerRepository.save(questionAnswer);
    }

    @Override
    public List<QuestionAnswer> findAllQuestionAnswer(String ownerEmail) {
        GroupRequest groupRequest = groupRequestRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("동아리가 없습니다.");
        });

        return groupRequest.getQuestionAnswerList();
    }


}
