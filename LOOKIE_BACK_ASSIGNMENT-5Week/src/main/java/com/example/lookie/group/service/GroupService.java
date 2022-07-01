package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;

import java.util.List;

public interface GroupService {
    Long makeGroup(String name, String description, String ownerEmail);
    void modifyName(String changeName, String ownerEmail);
    void modifyDescription(String description, String ownerEmail);
    // List<Question> findQuestions(Group group);
    List<Group> findGroups();
    Group findOneByName(String name);
    // void delete(Group group);

    // 정답 참고하여 수정
    void deleteGroup(String ownerEmail);
    List<Question> findQuestions(String ownerEmail);

}
