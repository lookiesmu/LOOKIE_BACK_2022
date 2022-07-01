package com.example.lookie.repository;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")

    void signup(){
        /**
         * given
         */
        Address address1 = new Address("경기도", "a", "123-456");
        Address address2 = new Address("서울", "b", "789-012");

        Member member1 = Member.createAdminMember("abcd123", "1234", "kim", address1);
        Member member2 = Member.createUserMember("efgh123", "5678", "lee", address2);

        /**
         * when
         */
        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        /**
         * then
         */
        Assertions.assertThat(result1.getEmail()).isEqualTo(member1.getEmail());
        Assertions.assertThat(result2.getEmail()).isEqualTo(member2.getEmail());
    }

    @Test
    @DisplayName("비밀변호 변경")
    void modifyPassword(){
        /**
         * given
         */
        Address address1 = new Address("경기도", "a", "123-456");
        Address address2 = new Address("서울", "b", "789-012");

        Member member1 = Member.createAdminMember("abcd123", "1234", "kim", address1);
        Member member2 = Member.createUserMember("efgh123", "5678", "lee", address2);

        /**
         * when
         */

        /**
         * then
         */
    }

    @Test
    @DisplayName("이름 변경")
    void modifyName(){
        /**
         * given
         */
        Address address1 = new Address("경기도", "a", "123-456");
        Address address2 = new Address("서울", "b", "789-012");

        Member member1 = Member.createAdminMember("abcd123", "1234", "kim", address1);
        Member member2 = Member.createUserMember("efgh123", "5678", "lee", address2);


        /**
         * when
         */

        /**
         * then
         */
    }
}
