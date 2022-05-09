package com.example.lookie.member.repository;

import com.example.lookie.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByEmail(String email);

}
