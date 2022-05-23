package com.example.lookie.grouprequest.Service;

import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.member.repository.MemberRepository;
import com.example.lookie.service.MemberService1;
import com.example.lookie.service.MemberService1Impl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
public class GroupRequestServiceTest {
    // test 주체
    GroupRequestService groupRequestService;

    // test 협력자
    @MockBean
    GroupRequestRepository groupRequestRepository;

    // test를 실행하기 전마다 GroupRequestService에 가짜 객체를 주입시켜준다.
    @BeforeEach
    void setup(){
        groupRequestService = new GroupRequestServiceImpl(groupRequestRepository);
    }
}