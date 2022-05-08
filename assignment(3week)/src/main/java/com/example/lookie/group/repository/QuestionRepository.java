package com.example.lookie.group.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private EntityManager em;

    public void save(Question question) {
        em.persist(question);
    }

    public Question findOne(Long id) {
        return em.find(Question.class, id);
    }

    public List<Question> findById(Long id) {
        return em.createQuery("select q from Question q where q.id = :id", Question.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void deleteQuestion(Long id) {
        em.remove(id);
    }
}
