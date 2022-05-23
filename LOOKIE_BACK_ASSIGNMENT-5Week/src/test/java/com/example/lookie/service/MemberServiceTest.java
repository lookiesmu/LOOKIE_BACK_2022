package com.example.lookie.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;
import com.example.lookie.member.repository.MemberRepository;
import com.example.lookie.member.service.MemberImpl;
import com.example.lookie.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

    // Test 주체
    MemberImpl memberImpl;

    // Test 협력자
    @MockBean
    MemberRepository memberRepository;

    // Test를 실행하기 전마다 MemberService에 가짜 객체를 주입
    @BeforeEach
    void setup(){
        memberImpl = new MemberImpl(memberRepository);
    }

    @Test
    @DisplayName("회원 가입 성공")
    void signupSuccess(){
        /**
         * given
         */
        Address address3 = new Address("경기도", "a", "123-456");
        Address address4 = new Address("서울", "b", "789-012");

        Member member3 = Member.createAdminMember("abcd123", "12", "kim", address3);
        Member member4 = Member.createUserMember("efgh", "56", "yun", address4);
        ReflectionTestUtils.setField(member3,"id",3l);
        ReflectionTestUtils.setField(member4,"id",3l);

        Mockito.when(memberRepository.save(member3)).thenReturn(member3); // 가짜 객체 응답 정의
        Mockito.when(memberRepository.save(member4)).thenReturn(member4); // 가짜 객체 응답 정의

        /**
         * when
         */
        Role roleAdmin = Role.ROLE_ADMIN;
        Role roleUser = Role.ROLE_USER;

        Long abcd123 = memberImpl.signup("abcd123", "12", roleAdmin, "kim", address3);

        /**
         * then
         */
        Assertions.assertThat(abcd123).isEqualTo(3L);
    }

    @Test
    @DisplayName("회원가입 시 email 동일하면 예외 발생")
    void signupFail(){
        /**
         * given
         */

        Address address1 = new Address("서울", "b", "789-012");
        Member member1 = Member.createAdminMember("abcd123", "12", "kim", address1);

        Role roleAdmin = Role.ROLE_ADMIN;

        Mockito.when(memberRepository.findByEmail("abcd123")).thenReturn(Optional.of(member1));

        /**
         * when then
         */
        Assertions.assertThatThrownBy(()->
                        memberImpl.signup("abcd13", "12", roleAdmin, "kim", address1)).
                isInstanceOf(IllegalStateException.class);
    }

}
