package com.example.lookie.group.domain.repository;


import com.example.lookie.group.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface QuestionRepository extends JpaRepository<Question, Long> {
}
