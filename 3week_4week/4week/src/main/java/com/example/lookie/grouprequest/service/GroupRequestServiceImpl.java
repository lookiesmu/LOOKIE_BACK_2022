package com.example.lookie.grouprequest.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.repository.GroupRepository;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupRequestServiceImpl implements GroupRequestService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final GroupRequestRepository groupRequestRepository;

    @Transactional
    @Override
    public void createGroupRequest(String email, String name, Department department, QuestionAnswer... questionAnswers) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 멤버 접근입니다.");
        });

        Group group = groupRepository.findByName(name).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 그룹 접근입니다.");
        });

        GroupRequest groupRequest = GroupRequest.createGroupRequest(member, group, department, questionAnswers);

        groupRequestRepository.save(groupRequest);
    }

    @Override
    public List<GroupRequest> findAllGroupRequest() {
        return groupRequestRepository.findAll();
    }

    @Transactional
    @Override
    public void modifyRequestStatus(Long groupRequestId, RequestStatus requestStatus) {

        GroupRequest groupRequest = groupRequestRepository.findById(groupRequestId).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 그룹요청 접근입니다.");
        });

        groupRequest.modifyRequestStatus(requestStatus);
    }
}
