package com.example.lookie.grouprequest.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.grouprequest.repository.GroupRequestRepository;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import com.example.lookie.question.domain.Question;
import com.example.lookie.question.repository.QuestionRepository;
import com.example.lookie.questionanswer.QuestionAnswer;
import com.example.lookie.questionanswer.dto.QuestionAnswerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupRequestServiceImpl implements GroupRequestService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final GroupRequestRepository groupRequestRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    @Override
    public Long createGroupRequest(String memberEmail, String groupName, Department department, List<QuestionAnswerRequestDto> questionAnswerRequestDtos) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> {
           throw new IllegalArgumentException("유효하지 않은 멤버 접근입니다.");
        });

        Group group = groupRepository.findByName(groupName).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 그룹 접근입니다.");
        });

        groupRequestRepository.findByMemberEmailAndGroupName(memberEmail, groupName).ifPresent(a -> {
            throw new IllegalArgumentException("이미 동아리를 개설하신 기록이 있습니다.");
        });

        List<QuestionAnswer> questionList = questionAnswerRequestDtos.stream()
                .map(a -> {
                    Question question = questionRepository.findById(a.getQuestionId()).get();
                    QuestionAnswer questionAnswer = QuestionAnswer.createRequestQuestion(question, a.getTitle(), a.getAnswer());
                    return questionAnswer;
                })
                .collect(Collectors.toList());

        GroupRequest groupRequest = GroupRequest.createGroupRequest(member, group, department, (QuestionAnswer[])questionList.toArray());

        return groupRequestRepository.save(groupRequest).getId();
    }

    @Override
    public List<GroupRequest> findAllGroupRequest(String ownerEmail) {
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("유효하지 않은 그룹 접근입니다.");
        });

        if(ownerEmail.equals(group.getOwnerEmail())){
            throw new IllegalArgumentException("해당 권한이 없습니다.");
        }
        return group.getGroupRequestList();
    }

    @Override
    public void acceptGroupRequest(Long groupRequestId, String ownerEmail) {
        GroupRequest groupRequest = groupRequestRepository.findFetchJoinById(groupRequestId).orElseThrow(() -> {
            throw new IllegalArgumentException("찾으시려는 요청이 존재하지 않습니다.");
        });

        if(ownerEmail.equals(groupRequest.getGroup().getOwnerEmail())){
            throw new IllegalArgumentException("해당 권한이 없습니다.");
        }
        groupRequest.modifyRequestStatus(RequestStatus.ACCEPT);
    }

    @Override
    public void rejectGroupRequest(Long groupRequestId, String ownerEmail) {
        GroupRequest groupRequest = groupRequestRepository.findFetchJoinById(groupRequestId).orElseThrow(() -> {
            throw new IllegalArgumentException("찾으시려는 요청이 존재하지 않습니다.");
        });

        if(ownerEmail.equals(groupRequest.getGroup().getOwnerEmail())){
            throw new IllegalArgumentException("해당 권한이 없습니다.");
        }
        groupRequest.modifyRequestStatus(RequestStatus.REJECTION);
    }

    @Override
    public List<QuestionAnswer> findGroupRequestQuestionAnswer(Long groupRequestId, String ownerEmail) {
        GroupRequest groupRequest = groupRequestRepository.findFetchJoinById(groupRequestId).orElseThrow(() -> {
            throw new IllegalArgumentException("찾으시려는 요청이 존재하지 않습니다.");
        });

        if(ownerEmail.equals(groupRequest.getGroup().getOwnerEmail())){
            throw new IllegalArgumentException("해당 권한이 없습니다.");
        }
        return groupRequest.getQuestionAnswerList();
    }
}
