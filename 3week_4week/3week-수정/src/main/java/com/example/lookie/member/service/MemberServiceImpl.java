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
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long joinAdmin(String email, String password, String name, Address address) {

        memberRepository.findByEmail(email).ifPresent(a -> {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        });

        Member admin = Member.createAdminMember(email, password, name, address);

        memberRepository.save(admin);
        return admin.getId();
    }

    @Override
    public Long joinUser(String email, String password, String name, Address address) {
        memberRepository.findByEmail(email).ifPresent(a -> {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        });

        Member user = Member.createUserMember(email, password, name, address);

        memberRepository.save(user);
        return user.getId();
    }

    @Override
    public void modifyMemberPassword(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        });

        member.modifyPassword(password);
    }

    @Override
    public void modifyMemberName(String email, String name) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        });

        member.modifyName(name);
    }


    @Override
    public void deleteMember(String email) {
        memberRepository.deleteByEmail(email);
    }
}
