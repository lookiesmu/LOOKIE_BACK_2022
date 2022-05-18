package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;

public interface MemberService {

    Long joinAdmin(String email, String password, String name, Address address);
    Long joinUser(String email, String password, String name, Address address);

    void modifyMemberPassword(String email, String password);
    void modifyMemberName(String email, String name);
    void deleteMember(String email);
}