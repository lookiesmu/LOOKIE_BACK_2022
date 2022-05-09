package com.example.lookie.group.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Question question){

        em.persist(question);
    }

    public Question findOne(long id){

        return em.find(Question.class, id);
    }


    public List<Question> findByName(Long id){
        return em.createQuery("select i from Question where q.id = :id", Question.class)
                .setParameter("id",id)
                .getResultList();
    }


    public void deleteQuestion(Long id) {
        em.remove(id);
    }

}
