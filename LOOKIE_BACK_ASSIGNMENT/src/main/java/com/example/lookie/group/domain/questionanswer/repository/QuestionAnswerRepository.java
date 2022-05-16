package com.example.lookie.group.domain.questionanswer.repository;

import com.example.lookie.group.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
