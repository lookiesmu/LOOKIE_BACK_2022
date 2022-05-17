package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;

import java.util.List;


public interface GroupService {
    Long createGroup(String name, String description, String ownerEmail);

    List<Question> findQuestionList(String name);
    List<Group> findAllGroup();
    Group findOneByName(String name);

    void deleteGroup(String name);
    void modifyGroupName(String modifyName, String ownerEmail);
    void modifyDescription(String modifyDescription, String ownerEmail);
}
