package com.example.lookie.group.domain.Repository;

import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.group.domain.domain.Question;
import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {
    private final EntityManager em;

    public void save(Question question){
        em.persist(question);
    }

    public Question findOne(Long id){
        return em.find(Question.class, id);
    }

    public List<Question> findById(Long id) {
        return em.createQuery("select q from Question q where q.id = :id", Question.class)
                .setParameter("id", id)
                .getResultList();
    }
    public void remove(Long id){
        em.remove(id);
    }
}
