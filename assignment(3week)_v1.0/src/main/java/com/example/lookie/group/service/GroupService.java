package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private GroupRepository groupRepository;

    /**
     * 그룹 생성
     */
    @Transactional
    public Long createGroup(String name, String description, String ownerEmail) {
        validateDuplicateName(name);
        validateDuplicateOwnerEmail(ownerEmail);

        Group group = Group.createGroup(name, description, ownerEmail);

        groupRepository.save(group);
        return group.getId();
    }

    private void validateDuplicateName(String name) {
        List<Group> findGroups = groupRepository.findByName(name);
        if (!findGroups.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 동아리명 입니다.");
        }
    }

    private void validateDuplicateOwnerEmail(String email) {
        List<Group> findGroups = groupRepository.findByEmail(email);
        if (!findGroups.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 OwnerEmail 입니다.");
        }
    }

    /**
     * 그룹 수정
     */
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

    /**
     * 질문 조회
     */
    public List<Question> findQuestionList(String name) {
        validateDuplicateName(name);

        Group group = groupRepository.findOne(name);
        return group.getQuestionList();
    }

    /**
     * 그룹 삭제
     */
    @Transactional
    public void deleteGroup(Long memberId) {
        groupRepository.deleteGroup(memberId);
    }
}
