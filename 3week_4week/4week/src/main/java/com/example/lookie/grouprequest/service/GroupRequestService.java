package com.example.lookie.grouprequest.service;

import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;

import java.util.List;

public interface GroupRequestService {
    void createGroupRequest(String email, String name, Department department, QuestionAnswer... questionAnswers);
    List<GroupRequest> findAllGroupRequest();
    void modifyRequestStatus(Long groupRequestId, RequestStatus requestStatus);
}