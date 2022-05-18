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
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;

    /** 동아리 생성 **/
//    @Override
//    @Transactional
//    public Long makeGroup(Group group) {
//
//        validateDuplicateGroupName(group);
//        validateDuplicateOwnerEmail(group);
//        if(groupRepository.findByOwnerEmail(group.getOwnerEmail())!=null){
//            group.createGroup(group.getName(), group.getDescription(), group.getOwnerEmail());
//        }
//        groupRepository.save(group);
//        return group.getId();
//    }
//
//    /** 동아리 이름 중복 확인 **/
//    private void validateDuplicateGroupName(Group group) {
//        // EXCEPTION
//        List<Group> findGroupName = groupRepository.findByName(group.getName());
//        if(!findGroupName.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 동아리명입니다.");
//        }
//    }
//
//    /** 동아리 소유 여부 확인 **/
//    private void validateDuplicateOwnerEmail(Group group) {
//        // EXCEPTION
//        Group findGroupOwnerEmail = groupRepository.findByOwnerEmail(group.getOwnerEmail());
//        if(findGroupOwnerEmail!=null){
//            throw new IllegalStateException("이미 동아리를 생성하였습니다.");
//        }
//    }

    @Transactional
    @Override
    public Long makeGroup(String name, String description, String ownerEmail){
        groupRepository.findByName(name).ifPresent(a->{
            throw new IllegalArgumentException("그룹 이름이 이미 존재합니다.");
        });

        groupRepository.findByOwnerEmail(ownerEmail).ifPresent(a->{
            throw new IllegalArgumentException("그룹을 이미 생성하셨습니다.");
        });

        Group group = Group.createGroup(name, description, ownerEmail);

        return groupRepository.save(group).getId();
    }


    /** 동아리 수정 **/
    @Transactional
    @Override
    public void modifyName(String name, String ownerEmail){
        Group group = groupRepository.findByName(name).orElseThrow(() -> {
            throw new IllegalArgumentException("회원님이 만든 그룹이 존재하지 않습니다.");
        });
    }

    @Transactional
    @Override
    public void modifyDescription(String ownerEmail, String description){
        Group group = groupRepository.findByOwnerEmail(ownerEmail).orElseThrow(() -> {
            throw new IllegalArgumentException("회원님이 만든 그룹이 존재하지 않습니다.");
        });
        group.modifyGroupDescription(description);
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
    public void delete(Group group){
        groupRepository.delete(group);
    }
}
