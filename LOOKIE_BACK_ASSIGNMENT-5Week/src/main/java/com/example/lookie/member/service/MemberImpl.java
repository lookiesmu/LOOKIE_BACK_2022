package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class MemberImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 회원가입
    @Override
    public Long signup(String email, String password, Role role, String name, Address address){

        memberRepository.findByEmail(email).ifPresent(a -> {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        });

        Member member = CheckRoleAndCreateMember(email, password, role, name, address);

        return memberRepository.save(member).getId();
    }



    /** password와 name 수정 **/
    @Override
    @Transactional
    public void modifyPassword(String email, String password){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        });
        member.modifyPassword(password);
    }

    @Override
    @Transactional
    public void modifyName(String email, String name){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        });
        member.modifyName(name);
    }


    /** 멤버 삭제(탈퇴) 사항 **/
    @Override
    public void withdrawal(String email){
        memberRepository.deleteByEmail(email);
    }
    private Member CheckRoleAndCreateMember(String email, String password, Role role, String name, Address address) {
        if(role==Role.ROLE_ADMIN){
            return Member.createAdminMember(email, password, name, address);
        }else{
            return Member.createUserMember(email, password, name, address);
        }
    }
}
