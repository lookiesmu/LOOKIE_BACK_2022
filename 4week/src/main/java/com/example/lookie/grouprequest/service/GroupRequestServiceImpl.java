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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupRequestServiceImpl implements GroupRequestService{
    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final GroupRequestRepository groupRequestRepository;


    @Transactional
    @Override
    public void createGroupRequest(String email, String name, Department department, QuestionAnswer... questionAnswers) {
        memberRepository.findByEmail(email).ifPresent(a -> {
            throw new IllegalArgumentException("이미 존재하는 멤버입니다.");
        });
        Member member = (Member) memberRepository;

        groupRepository.findByName(name).ifPresent( a-> {
            throw new IllegalArgumentException("유효하지 않은 그룹 접근입니다.");
        });
        Group group = (Group) groupRepository;

        GroupRequest groupRequest = GroupRequest.createGroupRequest(member, group, department, questionAnswers);

        groupRequestRepository.save(groupRequest);
    }

    @Override
    public List<GroupRequest> findAllGroupRequest(String ownerEmail) {
        groupRequestRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("동아리가 없습니다.");
        });
        return groupRequestRepository.findAll();
    }
    @Transactional
    @Override
    public void modifyRequestStatus(String ownerEmail, RequestStatus requestStatus) {
        GroupRequest groupRequest = groupRequestRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("동아리가 없습니다.");
        });

        groupRequest.changeRequestStatus(requestStatus);
    }
}
