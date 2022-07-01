package com.example.lookie.grouprequest.service;

import com.example.lookie.grouprequest.domain.Department;
import com.example.lookie.grouprequest.domain.GroupRequest;
import com.example.lookie.questionanswer.QuestionAnswer;
import com.example.lookie.questionanswer.dto.QuestionAnswerRequestDto;

import java.util.List;

public interface GroupRequestService {
    Long createGroupRequest(String memberEmail, String groupName, Department department, List<QuestionAnswerRequestDto> questionAnswerRequestDtos);

    List<GroupRequest> findAllGroupRequest(String ownerEmail);

    void acceptGroupRequest(Long groupRequestId, String ownerEmail);
    void rejectGroupRequest(Long groupRequestId, String ownerEmail);

    List<QuestionAnswer> findGroupRequestQuestionAnswer(Long groupRequestId, String ownerEmail);
}
