package com.example.lookie.member.service;

import com.example.lookie.group.domain.Group;
import com.example.lookie.group.domain.repository.GroupRepository;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */

    @Transactional
    public Long joinAdmin (Member admin){
        validateDuplicate(admin);
        memberRepository.save(admin);
        return admin.getId();

    }
    public Long joinUser (Member user){
        validateDuplicate(user);
        memberRepository.save(user);
        return user.getId();

    }

     public void validateDuplicate(Member member) {
         List<Member> findMembers =
            memberRepository.findByEmail(member.getEmail());
         if (!findMembers.isEmpty()) {
             throw new IllegalStateException("이미 존재하는 이메일 입니다!");
         }
     }
     public List<Member> findMembers(){
        return memberRepository.findAll();

     }
     public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
     }
     public Group ChangeGroupName (String groupName){
         Group group = GroupRepository.findOne(groupName);
         group.ChangeGroupName(groupName);
     }


     public void deleteMember(Long memberId){
        memberRepository.deleteMember(memberId);
     }
     public void changePassword (Long memberId, String password){
        Member member = memberRepository.findOne(memberId);
        member.changePassword(password);
     }
     public void changeName (Long memberId, String name){
        Member member = memberRepository.findOne(memberId);
        member.changeName(name);
     }


}
