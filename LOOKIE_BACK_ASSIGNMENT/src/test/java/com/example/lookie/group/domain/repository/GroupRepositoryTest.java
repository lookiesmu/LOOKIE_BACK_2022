package com.example.lookie.group.domain.repository;

import com.example.lookie.group.domain.Group;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Test
    @DisplayName("그룹 만들기 테스트")
    void makeGroup(){
        /**
         * given
         */
        Group groupA = Group.createGroup("group1", "그룹입니다", "가나다라마바사");

        /**
         * when
         */
        Group resultA = groupRepository.save(groupA);

        /**
         * then
         */
        Assertions.assertThat(resultA.getOwnerEmail()).isEqualTo(groupA.getOwnerEmail());

    }

    @Test
    @DisplayName("이름 변경 테스트")
    void changeGroupName(){
        /**
         * given
         */
        Group groupA = Group.createGroup("group1", "그룹입니다", "가나다라마바사");
        groupRepository.save(groupA);

        /**
         * when
         */
        groupA.changeGroupName();

        /**
         * then
         */
        Assertions.assertThat(groupA.getName()).isNotEqualTo(groupRepository.findByGroupName());
    }

    @Test
    @DisplayName("그룹 설명 변경")
    void changeGroupDescription(){
        /**
         * given
         */
        Group groupA = Group.createGroup("group1", "그룹입니다", "가나다라마바사");
        groupRepository.save(groupA);

        /**
         * when
         */
        groupA.changeGroupDescription();

        /**
         * then
         */
        Assertions.assertThat(groupA.getName()).isNotEqualTo(groupRepository.findById());

    }


}