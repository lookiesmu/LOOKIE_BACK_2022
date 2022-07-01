package com.example.lookie.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.group.service.GroupServiceImpl;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.grouprequest.service.GroupRequestServiceImpl;
import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
public class GroupRequestServiceTest {
    // Test 주체
    GroupRequestServiceImpl groupRequestServiceImpl;

    // Test 협력자
    @MockBean
    GroupRequestRepository groupRequestRepository;

    // Test를 실행하기 전마다 GroupRequestServiceImpl에 가짜 객체를 주입시켜준다.
    @BeforeEach
    void setUp(){
        groupRequestServiceImpl = new GroupRequestServiceImpl(groupRequestRepository);
    }

    @Test
    @DisplayName("그룹 가입요청 성공")
    void createGroupRequestSuccess(){
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

        ReflectionTestUtils.setField(groupRequest1,"group_request_id",3l);

        Mockito.when(groupRequestRepository.save(groupRequest1)).thenReturn(groupRequest1); // 가짜 객체 응답 정의

        /**
         * when
         */

        /**
         * then
         */
    }
}
