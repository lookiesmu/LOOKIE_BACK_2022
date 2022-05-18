package com.example.lookie.grouprequest.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class GroupRequestServiceImpl implements GroupRequestService{
    private final GroupRepository groupRepository;
    private final GroupRequestRepository groupRequestRepository;

    /**
     * GroupRequest 생성(그룹 가입 요청)
     */
    @Transactional
    @Override
    public Long makeGroupRequest(String ownerEmail, Member member, Group group,
                                 Department department, QuestionAnswer...questionAnswers) {

        GroupRequest groupRequest = GroupRequest.createGroupRequest(member, group, department, questionAnswers);
        return groupRequestRepository.save(groupRequest).getId();
    }

    /**
     * GroupRequest 조회
     */
    @Override
    public List<GroupRequest> checkGroupRequest(String ownerEmail) {
        groupRequestRepository.findByOwnerEmail(ownerEmail).orElseThrow(()-> {
            throw new IllegalArgumentException("회원님이 만든 그룹이 존재하지 않습니다.");
        });

        return groupRequestRepository.findAll();
    }

    /**
     * GroupRequest 수정
     */
    @Transactional
    @Override
    public void editRequest(String ownerEmail, GroupRequest groupRequest) {
        groupRequest = groupRequestRepository.findByOwnerEmail(ownerEmail).orElseThrow(()-> {
            throw new IllegalArgumentException("회원님이 만든 그룹이 존재하지 않습니다.");
        });

        groupRequest.changeRequestStatus(groupRequest.getRequestStatus());
    }
}
