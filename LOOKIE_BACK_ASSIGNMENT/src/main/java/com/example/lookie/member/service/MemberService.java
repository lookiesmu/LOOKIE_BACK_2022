package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;


public interface MemberService {
    Long joinAdmin(String name, String email, String password, Address address);
    Long joinUser(String name, String email, String password, Address address);
    void changeMemberPassword (String email, String password);
    void changeMemberName (String email, String name);
    void DeleteMember (String email);
}
