package com.example.lookie.member.repository;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 만들기")
    void createMember(){
        //given
        Address address1 = new Address("서울","A","1333");
        Address address2 = new Address("경기도","B","1233");
        Member member1 = Member.createAdminMember("abcd","1234","Oh",address1);
        Member member2 = Member.createUserMember("abc","123","woo",address2);

        //when
        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        //then
        Assertions.assertThat(result1.getEmail()).isEqualTo(member1.getEmail());
        Assertions.assertThat(result2.getEmail()).isEqualTo(member2.getEmail());

    }


}