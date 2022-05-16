package com.example.lookie.grouprequest.Service;

import com.example.lookie.group.domain.domain.QuestionAnswer;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.member.domain.Member;

import java.util.List;

public interface GroupRequestService {
    Long CreateGroupRequest (String Email, String Name, Member member, Department department,
                             QuestionAnswer... questionAnswers);
    List<GroupRequest> findAllGroupRequest(String ownerEmail);
    void ChangeRequestState(String ownerEmail, RequestStatus requestStatus, GroupRequest groupRequest);
}
