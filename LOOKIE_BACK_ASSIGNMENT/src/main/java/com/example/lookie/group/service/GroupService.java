package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.repository.GroupRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    public Long createGroup(Group group) {

        validateDuplicateName(group.getName()); //중복 회원 검증
        groupRepository.save(group);
        return group.getId();
    }

    private void validateDuplicateName(String name) {
        List<Group> findGroups = GroupRepository.findByName(name);
        if (!findGroups.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 동아리입니다.");
        }
    }


    @Transactional
    public void modifyGroupDescription(String name, String description) {
        Group group = groupRepository.findOne(name);
        group.modifyDescription(description);
    }

    @Transactional
    public void modifyGroupName(String name) {
        validateDuplicateName(name);

        Group group = groupRepository.findOne(name);
        group.modifyName(name);
    }

    @Transactional
    public void deleteGroup(Long memberId) {
        groupRepository.deleteGroup(memberId);
    }
}
