package com.example.lookie.group.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.Question;
import com.example.lookie.group.repository.GroupRepository;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    /** 동아리 생성 **/
    @Transactional
    public Long makeGroup(Group group) {

        validateDuplicateGroupName(group);
        validateDuplicateOwnerEmail(group);
        if(!groupRepository.findByOwnerEmail(group.getOwnerEmail()).isEmpty()){
            group.createGroup(group.getName(), group.getDescription(), group.getOwnerEmail());
        }
        groupRepository.save(group);
        return group.getId();
    }

    /** 동아리 이름 중복 확인 **/
    private void validateDuplicateGroupName(Group group) {
        // EXCEPTION
        List<Group> findGroupName = groupRepository.findByName(group.getName());
        if(!findGroupName.isEmpty()){
            throw new IllegalStateException("이미 존재하는 동아리명입니다.");
        }
    }

    /** 동아리 소유 여부 확인 **/
    private void validateDuplicateOwnerEmail(Group group) {
        // EXCEPTION
        List<Group> findGroupOwnerEmail = groupRepository.findByOwnerEmail(group.getOwnerEmail());
        if(!findGroupOwnerEmail.isEmpty()){
            throw new IllegalStateException("이미 동아리를 생성하였습니다.");
        }
    }


    /** 동아리 수정 **/
    @Transactional
    public void modifyName(Group group, String name){
        if(!groupRepository.findByOwnerEmail(group.getOwnerEmail()).isEmpty()){
            validateDuplicateGroupName(group);
            group.modifyGroupName(name);
        }
    }

    @Transactional
    public void modifyDescription(Group group, String description){
        if(!groupRepository.findByOwnerEmail(group.getOwnerEmail()).isEmpty()){
            group.modifyGroupDescription(description);
        }
    }


    /** 동아리에 해당하는 질문 조회 **/
    public List<Question> findQuestions(Group group){
        return groupRepository.findAllQuestion(group);
    }

    /** 동아리 전체 조회 **/
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    /** 이름으로 단건 조회 **/
    public Group findOneByName(String name) {
        return groupRepository.findOneByName(name);
    }

    /** 동아리 삭제 사항 **/
    private void delete(Group group){
        groupRepository.delete(group);
    }
}
