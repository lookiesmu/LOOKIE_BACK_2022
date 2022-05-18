package com.example.lookie.questionanswer.service;

import com.example.lookie.group.domain.Question;
import com.example.lookie.group.repository.QuestionRepository;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.questionanswer.domain.QuestionAnswer;
import com.example.lookie.questionanswer.repository.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    private final QuestionRepository questionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final GroupRequestRepository groupRequestRepository;

    @Transactional
    @Override
    public void createQuestionAnswer(Long questionId, String answer, String title) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> {
           throw new IllegalArgumentException("유효하지 않은 질문 접근입니다.");
        });

        QuestionAnswer questionAnswer = QuestionAnswer.createRequestQuestion(question, answer, title);
        questionAnswerRepository.save(questionAnswer);
    }

    @Override
    public List<QuestionAnswer> findAllQuestionAnswer(Long groupRequestId) {
        GroupRequest groupRequest = groupRequestRepository.findById(groupRequestId).orElseThrow(() -> {
           throw new IllegalArgumentException("유효하지 않은 그룹요청 접근입니다.");
        });

        return groupRequest.getQuestionAnswerList();
    }
}
