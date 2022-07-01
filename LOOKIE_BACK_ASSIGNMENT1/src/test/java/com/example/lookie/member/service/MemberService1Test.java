package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;
import com.example.lookie.member.repository.MemberRepository;
import com.example.lookie.service.MemberService1;
import com.example.lookie.service.MemberService1Impl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
public class MemberService1Test {
    // test 주체
    MemberService1 memberService1;

    // test 협력자
    @MockBean
    MemberRepository memberRepository;

    // test를 실행하기 전마다 MemberService에 가짜 객체를 주입시켜준다.
    @BeforeEach
    void setup(){
        memberService1 = new MemberService1Impl(memberRepository);
    }

    @Test
    @DisplayName("admin 멤버 생성 성공")
    void createAdminMember(){
        //given

        Address address1 = new Address("서울","A","1333");
        Member member1 = Member.builder().email("abcd").password("1234").name("Oh").address(address1).build();
        ReflectionTestUtils.setField(member1,"id",3l);

        Mockito.when(memberRepository.save(member1)).thenReturn(member1);

        //when
        Long Oh = memberService1.JoinAdmin("abcd","1234","Oh",address1);

        // then
        Assertions.assertThat(Oh).isEqualTo(3L);
    }
    @Test
    @DisplayName("user 멤버 생성 성공")
    void createUserMember(){
        //given

        Address address1 = new Address("서울","A","1333");
        Member member1 = Member.builder().email("abcd").password("1234").name("Oh").address(address1).build();
        ReflectionTestUtils.setField(member1,"id",3l);

        Mockito.when(memberRepository.save(member1)).thenReturn(member1);

        //when
        Long Oh = memberService1.JoinUser("abcd","1234","Oh",address1);

        // then
        Assertions.assertThat(Oh).isEqualTo(3L);
    }

    @Test
    @DisplayName("Admin 예외 발생")
    void createAdminFail(){
        //given
        Address address1 = new Address("서울","A","1333");
        Member member1 = Member.builder().email("abcd").password("1234").name("Oh").address(address1).build();
        Mockito.when(memberRepository.findByEmail("abcd")).thenReturn(Optional.of(member1));

        //when then
        Assertions.assertThatThrownBy(() -> memberService1.JoinAdmin("abcd","123","Oh",address1)).isInstanceOf(
                IllegalStateException.class);


    }
    @Test
    @DisplayName("User 예외 발생")
    void createUserFail(){
        //given
        Address address1 = new Address("서울","A","1333");
        Member member1 = Member.builder().email("abcd").password("1234").name("Oh").address(address1).build();
        Mockito.when(memberRepository.findByEmail("abcd")).thenReturn(Optional.of(member1));

        //when then
        Assertions.assertThatThrownBy(() -> memberService1.JoinUser("abcd","123","Oh",address1)).isInstanceOf(
                IllegalStateException.class);


    }

}

