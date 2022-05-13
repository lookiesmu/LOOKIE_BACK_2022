package com.example.lookie.member.repository;

import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member where m.email = :email", Member.class)
                .setParameter("Email", email)
                .getResultList();
    }

    public void deleteMember(Long id){
        em.remove(id);

    }
}
