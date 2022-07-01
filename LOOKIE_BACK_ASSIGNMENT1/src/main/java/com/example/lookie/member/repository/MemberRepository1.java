package com.example.lookie.member.repository;

import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository1 {


    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);

    }
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findById(Long id) {
        return em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email ", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    public void remove(Member member){
        em.remove(member);
    }

}
