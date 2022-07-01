package com.example.lookie.repository;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class GroupRequestRepositoryTest {
    @Autowired
    GroupRequestRepository groupRequestRepository;

    @Test
    @DisplayName("그룹가입요청")
    void createGroupRequest(){

        /**
         * given
         */
        Address address1 = new Address("경기도", "a", "123-456");
        Member member1 = Member.createUserMember("abcd123", "1234", "kim", address1);
        Group group1 = Group.createGroup("ABC", "그룹설명", "aaa");
        Department department1 = Department.BACK_END;
        Question question1 = Question.createQuestion(group1, "질문 제목");
        QuestionAnswer questionAnswer1 = QuestionAnswer.createRequestQuestion(question1,"답", "질문 제목");

        GroupRequest groupRequest1 = GroupRequest.createGroupRequest(member1, group1, department1, questionAnswer1);

        /**
         * when
         */
        GroupRequest result1 = groupRequestRepository.save(groupRequest1);

        /**
         * then
         */
        Assertions.assertThat(result1.getMember().getEmail()).isEqualTo(groupRequest1.getMember().getEmail());
    }

    @Test
    @DisplayName("그룹 request 조회")
    void groupRequestList(){

        /**
         * given
         */
        Address address1 = new Address("경기도", "a", "123-456");
        Member member1 = Member.createAdminMember("abcd123", "1234", "kim", address1);
        Group group1 = Group.createGroup("ABC", "그룹설명", "aaa");
        Department department1 = Department.BACK_END;
        Question question1 = Question.createQuestion(group1, "질문 제목");
        QuestionAnswer questionAnswer1 = QuestionAnswer.createRequestQuestion(question1,"답", "질문 제목");

        GroupRequest groupRequest1 = GroupRequest.createGroupRequest(member1, group1, department1, questionAnswer1);
        groupRequestRepository.save(groupRequest1);

        /**
         * when
         */
        List<GroupRequest> result = groupRequestRepository.findAll();

        /**
         * then
         */
        Assertions.assertThat(result.size()).isEqualTo(1);
    }
}
