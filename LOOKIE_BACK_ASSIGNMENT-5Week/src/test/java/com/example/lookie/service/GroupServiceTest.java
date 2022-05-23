package com.example.lookie.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.group.service.GroupService;
import com.example.lookie.group.service.GroupServiceImpl;
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
public class GroupServiceTest {

    // Test 주체
    GroupServiceImpl groupServiceImpl;

    // Test 협력자
    @MockBean
    GroupRepository groupRepository;

    // Test를 실행하기 전마다 GroupService에 가짜 객체를 주입시켜준다.
    @BeforeEach
    void setUp(){
        groupServiceImpl = new GroupServiceImpl(groupRepository);
    }

    @Test
    @DisplayName("그룹 생성 성공")
    void makeGroupSuccess(){
        /*
    given
     */
        Group group2 = Group.createGroup("Asdf","그룹설명", "abcdefd");
        ReflectionTestUtils.setField(group2,"id",3l);

        Mockito.when(groupRepository.save(group2)).thenReturn(group2); // 가짜 객체 응답 정의
    /*
    when
     */
        Long abcdefd = groupServiceImpl.makeGroup("Asdf", "그룹설명", "abcdefd");
    /*
    then
     */
        Assertions.assertThat(abcdefd).isEqualTo(3L);
    }

    @Test
    @DisplayName("그룹 생성시 group1과 이름이나 이메일이 같으면 예외 발생")
    void createGroupFail(){
        /**
         * given
         */
        Group group1 = Group.createGroup("Asdf","그룹설명", "abcdefd");
        Mockito.when(groupRepository.findByOwnerEmail("abcdefd")).thenReturn(Optional.of(group1));
        Mockito.when(groupRepository.findByName("Asdf")).thenReturn(Optional.of(group1));

        /**
         when then
         */
        Assertions.assertThatThrownBy(()->
                groupServiceImpl.makeGroup("Asdesf","그룹설명", "abcdefd")).isInstanceOf(IllegalStateException.class);
    }
}
