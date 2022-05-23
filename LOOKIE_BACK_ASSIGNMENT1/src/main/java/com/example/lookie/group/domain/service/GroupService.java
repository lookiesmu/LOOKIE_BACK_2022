package com.example.lookie.group.domain.service;


import com.example.lookie.group.domain.domain.Group;
import com.example.lookie.group.domain.Repository.GroupRepository;
import com.example.lookie.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    //그룹 생성
   @Transactional
    public long createGroup( String name, String description, String ownerEmail){
        validateDuplicateEmail(ownerEmail);
        validateDuplicateName(name);
        Group group = Group.createGroup(name, description, ownerEmail);
        groupRepository.save(group);
        return group.getId();
    }




    private void validateDuplicateName(String name) {
        List<Group> findGroups = groupRepository.findByName(name);
        if (!findGroups.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 Name 입니다");
        }
    }

    private void validateDuplicateEmail(String ownerEmail) {
        List<Group> findGroups = groupRepository.findByOnwerEmail(ownerEmail);
        if (!findGroups.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 OwnerEmail 입니다");
        }
    }
    //모든 Group 조회
    public List<Group> FindAllGroup() {
        return groupRepository.findAll();

    }
    //이름으로 단 건 조회
    public Group FindOneByName(String name) {
        return groupRepository.findOne(name);
    }
    // description 수정
    @Transactional
    public void ModifyDescription(Group group, String ownerEmail, String description) {
        List<Group> FindOwnerEmail = groupRepository.findByOnwerEmail(ownerEmail);
        if (!FindOwnerEmail.isEmpty()) {
            group.ModifyDescription(description);
        }
    }
    // name 수정
    @Transactional
    public void ModifyName(Group group, String ownerEmail, String name) {
        List<Group> FindName = groupRepository.findByName(name);
        if (!FindName.isEmpty()) {
            validateDuplicateName(name);
            group.ModifyName(name);
        }
    }
    //그룹 삭제
    @Transactional
    public void DeleteGroup(Group group){
        groupRepository.remove(group);
    }
}
