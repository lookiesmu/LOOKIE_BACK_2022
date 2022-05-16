package com.example.lookie.group.domain.service;

import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.group.domain.domain.Question;

import java.util.List;

public interface GroupService1 {
    long createGroup( String name, String description, String ownerEmail);
    List<Group> findAllGroup();
    Group FindOneByName(String name);
    void ModifyDescription(Group group, String ownerEmail, String description);
    List<Question> findQuestionList(String name);
    void ModifyName(Group group, String ownerEmail, String name);
    void DeleteGroup(String OwnerEmail);
}
