package com.example.lookie.member.repository;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void testMember() {
        /**
         * given
         */
        Member userMember = Member.createUserMember("dpdnjs0125@gmail.com", "1q2w3e4r", "kim", new Address("인천", "송도", "21212"));
        Member adminMember = Member.createAdminMember("yeahxne@gmail.com", "1q2w3e4r!", "lee", new Address("경기", "일산", "13451"));

        /**
         * when
         */
        Member saveUserMember = memberRepository.save(userMember);
        Member saveAdminMember = memberRepository.save(adminMember);

        /**
         * then
         */
        assertThat(saveUserMember.getId()).isEqualTo(userMember.getId());
        assertThat(saveUserMember.getName()).isEqualTo(userMember.getName());

        assertThat(saveAdminMember.getId()).isEqualTo(adminMember.getId());
        assertThat(saveAdminMember.getName()).isEqualTo(adminMember.getName());

        assertThat(saveUserMember).isEqualTo(userMember);
        assertThat(saveAdminMember).isEqualTo(adminMember);
    }
}