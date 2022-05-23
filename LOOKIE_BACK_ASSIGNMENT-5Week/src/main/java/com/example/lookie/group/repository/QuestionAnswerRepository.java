package com.example.lookie.group.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.grouprequest.domain.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
    Optional<QuestionAnswer> findByOwnerEmail(String OwnerEmail);

}
