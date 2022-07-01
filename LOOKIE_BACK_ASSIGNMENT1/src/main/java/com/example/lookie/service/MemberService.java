package com.example.lookie.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository1 memberRepository1;

    //회원가입 user
    @Transactional
    public Long JoinUser(String email, String password, String name, Address address) {
        validateDuplicateMember(email); //중복 회원 검증
        Member user = Member.createUserMember(email, password, name, address);
        memberRepository1.save(user);
        return user.getId();
    }
    //회원가입 admin
    @Transactional
    public Long JoinAdmin(String email, String password, String name, Address address){
        validateDuplicateMember(email);
        Member Admin = Member.createUserMember(email, password, name, address);
        memberRepository1.save(Admin);
        return Admin.getId();
    }
     //중복 이메일 검증
    private void validateDuplicateMember (String email) {
        List<Member> findMembers = memberRepository1.findByEmail(email);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }


    //이름 수정
    @Transactional
    public void ModifyMemberName(Member member, String email, String name) {
        List<Member> findEmails = memberRepository1.findByEmail(email);
        if (!findEmails.isEmpty()) {
            member.ModifyName(name);
        } else {
            throw  new IllegalStateException("존재 하지 않는 회원입니다.");
        }
    }
    //비밀번호 수정
    @Transactional
    public void ModifyMemberPassword(Member member, String email, String password){
        List<Member> findEmails = memberRepository1.findByEmail(email);
        if (!findEmails.isEmpty()) {
             member.ModifyPassword(password);
        } else {
            throw new IllegalStateException("존재 하지 않는 회원입니다.");
        }
    }
    // 회원 삭제
    @Transactional
    public void DeleteMember(Member member){
        memberRepository1.remove(member);
    }





    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository1.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository1.findOne(memberId);
    }
}
