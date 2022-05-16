package com.example.lookie.grouprequest.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.member.domain.Member;

import java.util.List;

public interface GroupRequestService {
    Long makeGroupRequest(String ownerEmail, Member member, Group group,
                          Department department, QuestionAnswer...questionAnswers);
    List<GroupRequest> checkGroupRequest(String ownerEmail);
    void editRequest(String ownerEmail, GroupRequest groupRequest);

}
