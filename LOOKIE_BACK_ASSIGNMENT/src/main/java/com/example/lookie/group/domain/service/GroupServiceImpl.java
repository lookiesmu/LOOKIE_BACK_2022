package com.example.lookie.group.domain.service;


import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.domain.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    @Override
    public Long createNewGroup (String groupName, String description, String email){

        groupRepository.findByOwnerEmail(email).ifPresent(a-> {
            throw new IllegalArgumentException("동아리는 하나만 생성 가능합니다.");
        });

        Group group = Group.createGroup(groupName, description, email);
        return group.getId();
    }

    @Override
    public List<Question> questionList (String groupName){
        Group group = groupRepository.findByGroupName(groupName).orElseThrow(() -> {
                throw new IllegalArgumentException("동아리의 이름이 잘못되었거나, 존재하지 않습니다.");
        });
        return group.getQuestionList();
    }

    @Override
    public Group findGroupName (String groupName){
        return groupRepository.findByGroupName(groupName).orElseThrow(() -> {
            throw new IllegalArgumentException("동아리의 이름이 잘못되었거나, 존재하지 않습니다.");
        });
    }

    @Override
    public List<Group> entireGroups (){
        return groupRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteGroup(String groupName){
        groupRepository.deleteByGroupName(groupName);
    }

    @Transactional
    @Override
    public void changeGroupName(String groupName, String ownerEmail){
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() ->{
            throw new IllegalArgumentException("동아리의 주인이 아닙니다");
        });

        groupRepository.findByGroupName(groupName).ifPresent(a -> {
            throw new IllegalArgumentException("사용중인 그룹이름입니다.");
        });

        group.changeGroupName(groupName);
    }

    @Transactional
    @Override
    public void changeGroupDescription(String description, String ownerEmail) {
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("동아리의 주인이 아닙니다");
        });
        group.changeGroupDescription(description);
    }
}

