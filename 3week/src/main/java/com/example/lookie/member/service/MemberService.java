package com.example.lookie.member.service;

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

    //유저 회원가입
    @Transactional
    public Long joinUser(Member member) {
        validateDuplicateEmail(member); //중복 이메일 검증

        Member user = Member.createUserMember(member.getEmail(),member.getPassword(),member.getName(), member.getAddress());//role정해주기
        memberRepository.save(user);
        return user.getId();
    }
    //어드민 회원가입
    @Transactional
    public Long joinAdmin(Member member) {
        validateDuplicateEmail(member); //중복 이메일 검증

        Member admin = Member.createAdminMember(member.getEmail(),member.getPassword(),member.getName(), member.getAddress());//role정해주기
        memberRepository.save(admin);
        return admin.getId();
    }
    //이메일중복
    private void validateDuplicateEmail(Member member) {
        List<Member> findMembers =
                memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
    //패스워드 수정
    @Transactional
    public void modifyMemberPassword(Member member){
        Member member1 = memberRepository.findOne(member.getId());
        member1.modifyPassword(member.getPassword());
    }
    //이름 수정
    @Transactional
    public void modifyMemberName(Member member){
        Member member2 = memberRepository.findOne(member.getId());
        member2.modifyName(member.getName());
    }


    //회원삭제
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    //회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
