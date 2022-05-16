package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;

import java.util.List;

public interface GroupService {
    Long makeGroup(String name, String description, String ownerEmail);
    void modifyName(String name, String ownerEmail);
    void modifyDescription(String ownerEmail, String description);
    List<Question> findQuestions(Group group);
    List<Group> findGroups();
    Group findOneByName(String name);
    void delete(Group group);

}
