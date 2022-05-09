package com.example.lookie.group.service;

import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

   /** 질문 생성 **/
   @Transactional
    private Long createQuestion(Question question) {
        if(question.getGroup()!=null){
            question.createQuestion(question.getGroup(), question.getTitle());
            questionRepository.save(question);
        }
        return question.getId();
    }

    /** 질문 삭제 **/
    @Transactional
    private void deleteQuestion(Question question) {
        if(questionRepository.findAllQA(question).isEmpty()){
            questionRepository.deleteById(question.getId());
        }
    }

    /** 질문 수정 **/
    @Transactional
    private void modifyQuestion(Question question, String title) {
        if(questionRepository.findAllQA(question).isEmpty()){
            question.modifyTitle(title);
        }
    }
}
