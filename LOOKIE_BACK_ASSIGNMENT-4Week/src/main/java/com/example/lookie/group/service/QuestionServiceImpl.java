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

public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final GroupRepository groupRepository;

   /** 질문 생성 **/
//   @Transactional
//    private Long createQuestion(Question question) {
//        if(question.getGroup()!=null){
//            question.createQuestion(question.getGroup(), question.getTitle());
//            questionRepository.save(question);
//        }
//        return question.getId();
//    }

    @Override
    public void addQuestion(String ownerEmail, String title){
       Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(()->{
           throw new IllegalArgumentException("생성된 그룹이 없습니다.");
        });

       Question question = Question.createQuestion(group, title);
       questionRepository.save(question);
    }

    /** 질문 삭제 **/
    @Transactional
    @Override
    public void deleteQuestion(Question question) {
        if(questionRepository.findAllQA(question).isEmpty()){
            questionRepository.deleteById(question.getId());
        }
    }

    /** 질문 수정 **/
    @Transactional
    @Override
    public void modifyQuestion(Question question, String title) {
        if(questionRepository.findAllQA(question).isEmpty()){
            question.modifyTitle(title);
        }
    }
}
