package com.example.lookie.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;

public interface MemberService1 {
    Long JoinUser(String email, String password, String name, Address address);
    Long JoinAdmin(String email, String password, String name, Address address);
    void ModifyMemberName(Member member, String email, String name);
    void ModifyMemberPassword(Member member, String email, String password);
    void DeleteMember(Member member);
}
