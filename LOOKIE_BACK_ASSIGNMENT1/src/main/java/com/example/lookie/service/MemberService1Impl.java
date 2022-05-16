package com.example.lookie.service;


import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService1Impl implements MemberService1{
    private final MemberRepository memberRepository;


    //회원가입 user
    @Override
    public Long JoinUser(String email, String password, String name, Address address) {
        memberRepository.findByEmail(email).ifPresent(a -> {
            throw new IllegalArgumentException("이메일이 중복됩니다.");
        }); //중복 회원 검증
        Member user = Member.createUserMember(email, password, name, address);

        return memberRepository.save(user).getId();
    }
    //회원가입 admin
    @Override
    public Long JoinAdmin(String email, String password, String name, Address address){
        memberRepository.findByEmail(email).ifPresent(a -> {
            throw new IllegalArgumentException("이메일이 중복됩니다.");
        });
        Member Admin = Member.createUserMember(email, password, name, address);

        return memberRepository.save(Admin).getId();
    }



    //이름 수정
    @Override
    public void ModifyMemberName(Member member, String email, String name) {
        memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("회원이 아닙니다.");
        });

        member.ModifyName(name);
    }
    //비밀번호 수정
    @Override
    public void ModifyMemberPassword(Member member, String email, String password){
        memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("회원이 아닙니다.");
        });

        member.ModifyPassword(password);
    }
    // 회원 삭제
    @Override
    public void DeleteMember(Member member)
    {
        memberRepository.delete(member);
    }
}
