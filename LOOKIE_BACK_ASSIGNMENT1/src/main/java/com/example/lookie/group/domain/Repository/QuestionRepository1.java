package com.example.lookie.group.domain.Repository;

import com.example.lookie.group.domain.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository1 extends JpaRepository<Question, Long> {

    Optional<Question> findById(Long id);

}
