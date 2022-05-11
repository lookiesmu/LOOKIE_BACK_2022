package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.repository.GroupRepository;
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

    @Transactional
    public Long joinGroup(Group group){
        validateName(group);
        //group 소유여부는 어떻게??
        validateOwnerEmail(group); //일단 ownerEmail중복여부로 판단...

        Group group1 = Group.createGroup(group.getName(), group.getDescription(),group.getOwnerEmail());
        groupRepository.save(group1);
        return group1.getId();

    }

    private void validateName(Group group) {
        List<Group> findGroups = groupRepository.findByName(group.getName());
        if (findGroups.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 동아리이름 입니다.");
        }
    }
    private void validateDuplicateName(Group group) {
        List<Group> findGroups = groupRepository.findByName(group.getName());
        if (!findGroups.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 동아리입니다.");
        }
    }
    private void validateOwnerEmail(Group group) {
        List<Group> findGroups = groupRepository.findByName(group.getOwnerEmail());
        if (findGroups.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 ownerEmail 입니다.");
        }
    }

    //동아리이름 수정
    @Transactional
    public void modifyGroupName(Group group){
        validateDuplicateName(group);//이름 중복 확인

        Group group2 = groupRepository.findOne(group.getName());
        group2.modifyName(group.getName());
    }
    //설명 수정
    @Transactional
    public void modifyGroupDescription(Group group){
        Group group3 = groupRepository.findOne(group.getName());
        group3.modifyDescription(group.getDescription());
    }

    //group 전체 조회
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }
    //group questionlist  조회
    public List<Question> findQuestions(Group group) {
        Group group4 = groupRepository.findOne(group.getName());

        return group4.getQuestionList();
    }
    //group 이름 조회
    public Group findOne(String name) {
        return groupRepository.findOne(name);
    }


    //회원삭제
    @Transactional
    public void deleteGroup(Long groupId) {
        groupRepository.deleteGroup(groupId);
    }
}
