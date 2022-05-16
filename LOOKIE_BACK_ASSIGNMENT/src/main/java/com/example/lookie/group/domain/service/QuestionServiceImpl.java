package com.example.lookie.group.domain.service;


import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.repository.GroupRepository;
import com.example.lookie.group.domain.repository.QuestionRepository;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final GroupRepository groupRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createNewQuestion (String newQuestion, String groupName){
        Group group = groupRepository.findByGroupName(groupName).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹이 틀렸거나 존재하지 않습니다.");
        });

        Question question = Question.createQuestion(group, newQuestion);
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestion (Long questionId, String ownerEmail, String userEmail){
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 이메일로 생성된 동아리가 없습니다.");
        });
        memberRepository.findByEmail(userEmail).ifPresent(a-> {
            throw new IllegalArgumentException("맴버가 있어서 수정 불가능합니다.");
        });
        questionRepository.deleteById(questionId);
    }

    @Override
    public void changeQuestionTitle (Long questionId, String changedQuestion, String userEmail){

        Question question = questionRepository.findById(questionId).orElseThrow(() -> {
            throw new IllegalArgumentException("잘못 입력하셨거나, 해당 질문이 없습니다.");
        });

        memberRepository.findByEmail(userEmail).ifPresent(a-> {
            throw new IllegalArgumentException("맴버가 있어서 수정 불가능합니다.");
        });
        question.changeTitle(changedQuestion);
    }
}
