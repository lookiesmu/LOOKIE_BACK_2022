package com.example.lookie.group.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.service.GroupService;
import com.example.lookie.member.repository.MemberRepository;
import com.example.lookie.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class GroupRepositoryTest {

    @Autowired GroupRepository groupRepository;
    @Autowired MemberRepository memberRepository;

    @Test
    void createGroupTest() {
        /**
         * given
         */
        Group group = Group.createGroup("동아리", "해당 동아리는 코딩 동아리입니다.", "dpdnjs0125@gmail.com");

        /**
         * when
         */
        Group saveGroup = groupRepository.save(group);

        /**
         * then
         */
        assertThat(saveGroup.getName()).isEqualTo(group.getName());
    }

    void findByOwnerTest() {
        /**
         * given
         */
        Group group = Group.createGroup("동아리", "해당 동아리는 코딩 동아리입니다.", "dpdnjs0125@gmail.com");

        /**
         * when
         */
        groupRepository.save(group);
        Group group1 = groupRepository.findByOwnerEmail(group.getOwnerEmail()).get();

        /**
         * then
         */
        assertThat(group.getName()).isEqualTo("동아리");
    }

    void findByNameTest() {
        /**
         * given
         */
        Group group = Group.createGroup("동아리", "해당 동아리는 코딩 동아리입니다.", "dpdnjs0125@gmail.com");

        /**
         * when
         */
        groupRepository.save(group);
        Group group1 = groupRepository.findByName(group.getName()).get();

        /**
         * then
         */
        assertThat(group.getOwnerEmail()).isEqualTo("pdnjs0125@gmail.com");
    }
}