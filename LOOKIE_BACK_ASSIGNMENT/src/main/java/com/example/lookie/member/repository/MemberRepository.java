package com.example.lookie.member.repository;



import com.example.lookie.member.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member where m.email = :email", Member.class)
                .setParameter("email",email)
                .getResultList();
    }

    public void deleteMember(Long id) {
        em.remove(id);
    }

}