package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Override
    public Long joinAdmin (String name, String email, String password, Address address){

        memberRepository.findByEmail(email).ifPresent(a ->{
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        });

        Member admin = Member.createAdminMember(name, email, password, address);

        memberRepository.save(admin);
        return admin.getId();
    }
    @Override
    public Long joinUser(String name, String email, String password, Address address){
        memberRepository.findByEmail(email).ifPresent(a ->{
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        });

        Member user = Member.createUserMember(name, email, password, address);

        memberRepository.save(user);
        return user.getId();
    }

    @Override
    public void changeMemberPassword(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("");
        });

        member.changePassword(password);
    }

    @Override
    public void changeMemberName(String email, String name) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("");
        });
        member.changeName(name);
    }

    @Override
    public void DeleteMember(String email) {
        memberRepository.deleteMember(email);

    }
}
