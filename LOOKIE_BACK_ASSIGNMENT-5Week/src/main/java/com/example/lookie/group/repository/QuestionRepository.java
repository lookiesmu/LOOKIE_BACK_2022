package com.example.lookie.group.repository;

import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // List<QuestionAnswer> findAllQA(Question question);

}
