package com.example.lookie.questionanswer.repository;

import com.example.lookie.group.domain.Question;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.questionanswer.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

}
