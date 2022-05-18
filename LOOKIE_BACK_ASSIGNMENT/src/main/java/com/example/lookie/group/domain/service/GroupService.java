package com.example.lookie.group.domain.service;


import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;

import java.util.List;

public interface GroupService {
    Long createNewGroup (String groupName, String description, String email);

    List<Question> questionList (String groupName);
    Group findGroupName (String groupName);
    List<Group> entireGroups();

    void deleteGroup(String groupName);
    void changeGroupName(String groupName, String ownerEmail);
    void changeGroupDescription (String ownerEmail, String description);


}