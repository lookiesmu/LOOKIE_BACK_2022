package com.example.lookie.grouprequest.Service;

import com.example.lookie.group.domain.Repository.GroupRepository1;
import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.group.domain.domain.QuestionAnswer;
import com.example.lookie.group.domain.service.GroupService1;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupRequestServiceImpl implements GroupRequestService {
    private final GroupRequestRepository groupRequestRepository;
    private final MemberRepository memberRepository;
    private final GroupRepository1 groupRepository1;


    //Group Request 생성
    @Transactional
    @Override
    public Long CreateGroupRequest (String Email, String Name, Member member, Department department,
                                    QuestionAnswer... questionAnswers) {
        memberRepository.findByEmail(Email).ifPresent(a -> {
            throw new IllegalArgumentException("이메일이 중복됩니다.");
        }); //이메일 중복확인

        Group group = groupRepository1.findByName(Name).orElseThrow(() -> {
            throw new IllegalArgumentException("신청하려는 그룹의 이름이 존재하지 않습니다.");
        });

        if (member.getRole() == Role.ROLE_ADMIN) {
            throw new IllegalStateException("Group Request를 생성 할 수 없습니다");
        } // role이 Admin일 경우
        GroupRequest groupRequest = GroupRequest.createGroupRequest(member, group, department, questionAnswers);

        return groupRequestRepository.save(groupRequest).getId();

    }
    @Override
    public List<GroupRequest> findAllGroupRequest(String ownerEmail){
        groupRepository1.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹을 소유하고 있지 않습니다.");
        });

        return groupRequestRepository.findAll();

    }

    @Transactional
    @Override
    public void ChangeRequestState(String ownerEmail, RequestStatus requestStatus, GroupRequest groupRequest){
        groupRepository1.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹을 소유하고 있지 않습니다.");
        });
        groupRequest.changeRequestStatus(requestStatus);



    }

}
