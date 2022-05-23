package com.example.lookie.group.domain.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {
    @Test
    @DisplayName("그룹의 이름이 바뀌는지 확인")
    void changeGroupNameTest() {
        //given
        Group group = Group.createGroup("a","b","cde");
        //when
        group.ModifyName("bc");
        //then
        Assertions.assertThat(group.getName()).isEqualTo("bc");
    }
    @Test
    @DisplayName("그룹의 이름이 바뀌는지 확인")
    void changeGroupDescriptionTest() {
        //given
        Group group = Group.createGroup("a","b","cde");
        //when
        group.ModifyDescription("bcd");
        //then
        Assertions.assertThat(group.getDescription()).isEqualTo("bcd");
    }

}