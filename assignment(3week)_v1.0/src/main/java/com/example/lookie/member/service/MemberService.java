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
     * 회원 가입(ADMIN, USER)
     */
    @Transactional
    public Long joinAdmin(String email, String password, String name, Address address) {
        validateDuplicateEmail(email);  //중복 이메일 검증
        Member admin = Member.createAdminMember(email, password, name, address);

        memberRepository.save(admin);
        return admin.getId();
    }

    @Transactional
    public Long joinUser(String email, String password, String name, Address address) {
        validateDuplicateEmail(email);
        Member user = Member.createUserMember(email, password, name, address);

        memberRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateEmail(String email) {
        List<Member> findMembers = memberRepository.findByEmail(email);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    /**
     * 회원 수정
     */
    @Transactional
    public void modifyMemberPassword(Long memberId, String password) {
        Member member = memberRepository.findOne(memberId);
        member.modifyPassword(password);
    }

    @Transactional
    public void modifyMemberName(Long memberId, String name) {
        Member member = memberRepository.findOne(memberId);
        member.modifyName(name);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteMember(Long memberId) {
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
