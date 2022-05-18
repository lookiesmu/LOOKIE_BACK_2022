package com.example.lookie.group.service;


import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    @Transactional
    @Override
    public Long createGroup(String name, String description, String ownerEmail) {
        groupRepository.findByName(name).ifPresent(a -> {
            throw new IllegalArgumentException("이미 존재하는 동아리명 입니다.");
        });
        groupRepository.findByOwnerEmail(ownerEmail).ifPresent(a-> {
            throw new IllegalArgumentException("이미 동아리를 생성했습니다.");
        });

        Group group = Group.createGroup(name, description, ownerEmail);
        groupRepository.save(group);

        return group.getId();
    }


    @Override
    public List<Question> findQuestionList(String name) {
        Group group = groupRepository.findByName(name).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 동아리명 입니다.");
        });
        return group.getQuestionList();
    }

    @Override
    public Group findOneByName(String name) {
        return groupRepository.findByName(name).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 동아리명 입니다.");
        });
    }

    @Override
    public List<Group> findAllGroup() {
        return groupRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteGroup(String name) {
        groupRepository.deleteByName(name);
    }

    @Transactional
    @Override
    public void modifyGroupName(String modifyName, String ownerEmail) {
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("사용자가 생성한 동아리가 없습니다.");
        });

        groupRepository.findByName(modifyName).ifPresent(a -> {
            throw new IllegalArgumentException("이미 존재하는 동아리명 입니다.");
        });
        group.modifyName(modifyName);
    }

    @Transactional
    @Override
    public void modifyDescription(String modifyDescription, String ownerEmail) {
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("사용자가 생성한 동아리가 없습니다.");
        });

        group.modifyDescription(modifyDescription);
    }
}
