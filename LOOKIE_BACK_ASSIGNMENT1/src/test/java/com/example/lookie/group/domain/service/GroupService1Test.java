package com.example.lookie.group.domain.service;

import com.example.lookie.group.domain.Repository.GroupRepository;
import com.example.lookie.group.domain.Repository.GroupRepository1;
import com.example.lookie.group.domain.domain.Group;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
public class GroupService1Test {
    // test 주체
    GroupService1 groupService1;

    // test 협력자
    @MockBean
    GroupRepository1 groupRepository1;

    //test를 실행하기 전마다 GroupService에 가짜 객체를 주입시켜준다.
    @BeforeEach
    void setUp(){
        groupService1 = new GroupServiceImpl(groupRepository1);
    }

    @Test
    @DisplayName("그룹 생성 성공")
    void createGroupSuccess() {
        //given

        Group group1 = Group.createGroup("a","b","ccc");
        ReflectionTestUtils.setField(group1,"id",3l);

        Mockito.when(groupRepository1.save(group1)).thenReturn(group1);

        //when
        Long aaa = groupService1.createGroup("a","b","ccc");

        //then

        Assertions.assertThat(aaa).isEqualTo(3L);
    }

    @Test
    @DisplayName("이름 중복, OwnerMail을 통해 그룹 소유한지 확인")
    void createGroupFail() {
        //given

        Group group1 = Group.createGroup("a","b","ccd");
        Mockito.when(groupRepository1.findByName("a")).thenReturn(Optional.of(group1));
        Mockito.when(groupRepository1.findByOwnerEmail("ccd")).thenReturn(Optional.of(group1));

        //when then
        Assertions.assertThatThrownBy(() -> groupService1.createGroup("a","b","ccd"))
                .isInstanceOf(IllegalStateException.class);



    }

}