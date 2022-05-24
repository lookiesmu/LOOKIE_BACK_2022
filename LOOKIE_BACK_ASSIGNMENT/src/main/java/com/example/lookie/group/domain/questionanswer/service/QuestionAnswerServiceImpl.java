package com.example.lookie.group.domain.questionanswer.service;

import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.domain.repository.QuestionRepository;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final GroupRequestRepository groupRequestRepository;


    @Override
    @Transactional
    public void createQuestionAnswer(Long questionId, String answer, String title){
            Question question = questionRepository.findById(questionId).orElseThrow(() -> {
                throw new IllegalArgumentException("");
            });

        QuestionAnswer questionAnswer = QuestionAnswer.createRequestQuestion(question, answer, title);
        questionAnswerRepository.save(questionAnswer);
    }
    public List<QuestionAnswer> findEveryQuestion (String ownerEmail, Long groupRequestId){

        GroupRequest groupRequest = groupRequestRepository.findById(groupRequestId).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 그룹요청 접근입니다.");
        });
        memberRepository.findByEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹 생성자만 확인 할 수 있습니다.");
        });
        return groupRequest.getQuestionAnswerList();
    }


}
