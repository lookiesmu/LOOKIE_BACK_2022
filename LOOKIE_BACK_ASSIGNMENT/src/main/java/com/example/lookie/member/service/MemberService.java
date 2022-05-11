package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long joinAdmin(Member member) {
        validateDuplicateEmail(member);  //중복 이메일 검증
        Member admin = Member.createAdminMember(member.getEmail(),member.getPassword(),member.getName(),member.getAddress());

        memberRepository.save(admin);
        return admin.getId();
    }
    public Long joinUser(Member member) {
        validateDuplicateEmail(member);  //중복 이메일 검증
        Member user = Member.createAdminMember(member.getEmail(),member.getPassword(),member.getName(),member.getAddress());

        memberRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateEmail(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
    // 이름 수정
    public void modifyMemberName(Long memberId, String name){
        Member member = memberRepository.findOne(memberId);
        member.modifyName(name);
    }
    // 비밀번호 수정
    public void modifyMemberPassword(Long memberId, String password){
        Member member = memberRepository.findOne(memberId);
        member.modifyPassword(password);
    }
    // 삭제
    public void deleteMember(Long memberId){
        memberRepository.deleteMember(memberId);
    }
    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
