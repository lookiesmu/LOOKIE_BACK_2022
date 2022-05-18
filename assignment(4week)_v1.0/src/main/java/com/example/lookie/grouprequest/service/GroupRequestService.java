package com.example.lookie.grouprequest.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;
import com.example.lookie.questionanswer.domain.QuestionAnswer;

import java.util.List;

public interface GroupRequestService {
    void createGroupRequest(String email, String name, Department department, QuestionAnswer... questionAnswers);
    List<GroupRequest> findAllGroupRequest();
    void modifyRequestStatus(Long groupRequestId, RequestStatus requestStatus);
}
