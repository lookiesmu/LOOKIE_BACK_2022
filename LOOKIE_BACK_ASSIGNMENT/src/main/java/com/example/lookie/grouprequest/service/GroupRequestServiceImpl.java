package com.example.lookie.grouprequest.service;


import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.domain.repository.GroupRepository;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupRequestServiceImpl implements GroupRequestService{

    private final GroupRequestRepository groupRequestRepository;
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createGroupRequest(String userEmail, String adminEmail, String groupName, Department department, QuestionAnswer... questionAnswers){

        Member member = memberRepository.findByEmail(userEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("일치한 정보가 없습니다.");
        });

        Group group = groupRepository.findByGroupName(groupName).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 그룹 접근입니다.");
        });

        memberRepository.findByEmail(adminEmail).ifPresent(a-> {
            throw new IllegalArgumentException("관리자는 지원 할 수 없습니다.");
        });

        GroupRequest groupRequest = GroupRequest.createGroupRequest(member, group, department, questionAnswers);
        groupRequestRepository.save(groupRequest);
    }
    @Override
    public List<GroupRequest> findAllGroupRequest(String ownerEmail){

        memberRepository.findByEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("동아리 생성자만 확인 가능합니다.");
        });
        return groupRequestRepository.findAll();
    }

    @Override
    @Transactional
    public void changeRequestState(String ownerEmail, RequestStatus requestStatus, Long groupRequestId){

        GroupRequest groupRequest = groupRequestRepository.findById(groupRequestId).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 그룹요청 접근입니다.");
        });

        memberRepository.findByEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("동아리 생성자만 확인 가능합니다.");
        });
        groupRequest.changeRequestStatus(requestStatus);
    }
}
