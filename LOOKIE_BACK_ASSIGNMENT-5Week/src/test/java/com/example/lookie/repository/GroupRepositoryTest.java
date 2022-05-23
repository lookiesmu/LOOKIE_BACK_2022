package com.example.lookie.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.repository.GroupRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Test
    @DisplayName("그룹 만들기")
    void makeGroup(){
        /**
         * given
         */
        Group group1 = Group.createGroup("A","그룹설명", "abcde");

        /**
         * when
         */
        Group result1 = groupRepository.save(group1);

        /**
         * then
         */
        Assertions.assertThat(result1.getOwnerEmail()).isEqualTo(group1.getOwnerEmail());
    }

    @Test
    @DisplayName("그룹 반환")
    void groupList(){
        /**
         * given
         */
        Group group1 = Group.createGroup("A","그룹설명", "abcde");
        groupRepository.save(group1);

        /**
         * when
         */
        List<Group> result = groupRepository.findAll();

        /**
         * then
         */
        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("이름 변경")
    void modifyGroupName(){
        /**
         * given
         */
        Group group1 = Group.createGroup("ABC","그룹설명", "abcde");
        groupRepository.save(group1);

        /**
         * when
         */
        group1.modifyGroupName();

        /**
         * then
         */
        Assertions.assertThat(group1.getName()).isNotEqualTo(groupRepository.findByName())
    }

    @Test
    @DisplayName("그룹 설명 변경")
    void modifyGroupDescription(){
        /**
         * given
         */
        Group group1 = Group.createGroup("ABC","그룹설명", "abcde");
        groupRepository.save(group1);

        /**
         * when
         */
        group1.modifyGroupDescription();

        /**
         * then
         */
        Assertions.assertThat(group1.getDescription()).isNotEqualTo(groupRepository.findById())
    }
}
