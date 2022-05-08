package com.example.lookie.group.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {
    private final EntityManager em;

    public void save(Group group){
        em.persist(group);
    }

    public Group findOne(String name){
        return em.find(Group.class,name);
    }

    public List<Group> findAll(){
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    public List<Group> findByName(String name) {
        return em.createQuery("select g from Group g where g.name = :name",
                        Group.class)
                .setParameter("name", name)
                .getResultList();
    }
    public void deleteGroup(Long id){
        em.remove(id);
    }

}
