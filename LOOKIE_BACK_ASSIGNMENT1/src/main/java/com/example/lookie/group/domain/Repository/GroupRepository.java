package com.example.lookie.group.domain.Repository;


import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private EntityManager em;

    public void save(Group group){
        em.persist(group);
    }

    public Group findOne(String name) {
        return em.find(Group.class, name);
    }


    public List<Group> findAll() {
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    public List<Group> findByName(String name){
        return em.createQuery("select g from Group g where g.name = : name", Group.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Group> findByOnwerEmail(String Onweremail){
        return em.createQuery("select g from Group g where g.ownerEmail = : owneremail", Group.class)
                .setParameter("owneremail", Onweremail)
                .getResultList();
    }

    public List<Group> findByDescription(String description){
        return em.createQuery("select g from Group g where g.description = : description", Group.class)
                .setParameter("description", description)
                .getResultList();
    }
    public void remove(Group group){
        em.remove(group);
    }
}

