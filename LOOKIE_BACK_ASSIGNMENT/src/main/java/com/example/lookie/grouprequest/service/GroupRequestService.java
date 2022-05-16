package com.example.lookie.grouprequest.service;


import com.example.lookie.group.domain.QuestionAnswer;
import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.grouprequest.domain.RequestStatus;
import com.example.lookie.member.domain.Role;

import java.util.List;

public interface GroupRequestService {

    void createGroupRequest(String userEmail, String adminEmail, String groupName, Department department, QuestionAnswer... questionAnswers);
    List<GroupRequest> findAllGroupRequest (String ownerEmail);
    void changeRequestState (String ownerEmail, RequestStatus requestStatus, Long groupRequestId);


}
