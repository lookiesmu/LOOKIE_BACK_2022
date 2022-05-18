package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import com.example.lookie.member.domain.Role;

public interface MemberService {
    Long signup(String email, String password, Role role, String name, Address address);
    void modifyPassword(String email, String password);
    void modifyName(String email, String name);
    void delete(Member member);
}
