package com.example.lookie.group.domain.Repository;

import com.example.lookie.group.domain.domain.Question;
import com.example.lookie.group.domain.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

}
