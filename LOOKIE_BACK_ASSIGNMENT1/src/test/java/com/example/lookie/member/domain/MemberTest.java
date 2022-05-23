package com.example.lookie.member.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    @Test
    @DisplayName("password 수정")
    void changePasswordTest() {
        //given
        Address address1 = new Address("서울","A","1333");
        Member member1 = Member.builder().email("abcd").password("1234").name("Oh").address(address1).build();

        //when
        member1.ModifyPassword("12345");

        //then
        Assertions.assertThat(member1.getPassword()).isEqualTo("12345");
    }

    @Test
    @DisplayName("name 수정")
    void changeNameTest() {
        //given
        Address address1 = new Address("서울","A","1333");
        Member member1 = Member.builder().email("abcd").password("1234").name("Oh").address(address1).build();

        //when
        member1.ModifyName("OhWoo");

        //then
        Assertions.assertThat(member1.getName()).isEqualTo("OhWoo");
    }
}