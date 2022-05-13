package com.example.lookie.group.domain.repository;

import com.example.lookie.group.domain.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class GroupRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Group group){
        em.persist(group);
    }

    public Group findOne(Long id){
        return em.find(Group.class, id);
    }

    public List<Group> findAll(){
        List<Group> result = em.createQuery("select g from Group g", Group.class)
                .getResultList();
        return result;
    }

    public void deleteGroup(Long id){
        em.remove(id);

    }
}

