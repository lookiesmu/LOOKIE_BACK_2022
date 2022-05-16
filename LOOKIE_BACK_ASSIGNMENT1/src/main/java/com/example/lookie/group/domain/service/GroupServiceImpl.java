package com.example.lookie.group.domain.service;

import com.example.lookie.group.domain.Repository.GroupRepository1;
import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.group.domain.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService1{
    private final GroupRepository1 groupRepository1;

    //그룹 생성
    @Override
    @Transactional
    public long createGroup( String name, String description, String ownerEmail){
        groupRepository1.findByName(name).ifPresent(a -> {
            throw new IllegalArgumentException("존재하는 이름 입니다.");
        });

        groupRepository1.findByOwnerEmail(ownerEmail).ifPresent(a -> {
            throw new IllegalArgumentException("이미 그룹을 소유하고 있습니다");
        });

        Group group = Group.createGroup(name, description, ownerEmail);

        return groupRepository1.save(group).getId();
    }

    // 모든 Group 조회
    @Override
    public List<Group> findAllGroup() {
        return groupRepository1.findAll();
    }
    //이름으로 단 건 조회
    @Override
    public Group FindOneByName(String name) {
        return groupRepository1.findByName(name).orElseThrow(() -> {
            throw new IllegalArgumentException("존재 하지 않는 이름입니다.");
        });
    }
    // description 수정
    @Override
    @Transactional
    public void ModifyDescription(Group group, String ownerEmail, String description) {
        groupRepository1.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹을 소유하고 있지 않습니다");
        });

        group.ModifyDescription(description);
    }
    // Group에 해당하는 Question 조회
    @Override
    public List<Question> findQuestionList(String name){
        Group group = groupRepository1.findByName(name).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹을 소유하고 있지 않습니다");
        });

        return group.getQuestionList();
    }

    // name 수정
    @Override
    @Transactional
    public void ModifyName(Group group, String ownerEmail, String name) {
        groupRepository1.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("그룹을 소유하고 있지 않습니다.");
        });

        groupRepository1.findByName(name).orElseThrow(() -> {
            throw new IllegalArgumentException("바꾸려는 이름이 이미 존재합니다.");
        });
        group.ModifyName(name);
    }

    //그룹 삭제
    @Override
    @Transactional
    public void DeleteGroup(String OwnerEmail){
        groupRepository1.deleteByOwnerEmail(OwnerEmail);
    }
}
