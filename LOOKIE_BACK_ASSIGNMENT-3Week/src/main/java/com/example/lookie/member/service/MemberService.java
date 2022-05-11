package com.example.lookie.member.service;

import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /** 회원 가입 **/
    // 관리자 역할
    @Transactional
    public Long joinAdminMember(Member member){

        validateDuplicateEmail(member); // 중복 이메일 검증
        Member adminMember= member.createAdminMember(member.getEmail(), member.getPassword(), member.getName(), member.getAddress());
        memberRepository.save(adminMember);

        return adminMember.getId();
    }

    // 관리자 아닌 멤버 역할
    @Transactional
    public Long joinUserMember(Member member){

        validateDuplicateEmail(member); // 중복 이메일 검증
        Member userMember= member.createUserMember(member.getEmail(), member.getPassword(), member.getName(), member.getAddress());
        memberRepository.save(userMember);

        return member.getId();
    }

    private void validateDuplicateEmail(Member member) {
        List<Member> findMembers  = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    /** password와 name 수정 **/
    @Transactional
    private void modifyPassword(Member member, String password){
        member.modifyPassword(password);
    }

    @Transactional
    private void modifyName(Member member, String name){
        member.modifyPassword(name);
    }


    /** 멤버 삭제(탈퇴) 사항 **/
    private void delete(Member member){
        memberRepository.delete(member);
    }

}
