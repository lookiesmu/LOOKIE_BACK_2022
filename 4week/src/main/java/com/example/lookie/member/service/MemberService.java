package com.example.lookie.member.service;

import com.example.lookie.member.domain.Address;
import com.example.lookie.member.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberService {

    Long joinUser(String email, String password, String name, Address address);
    Long joinAdmin(String email, String password, String name, Address address);

    void modifyMemberPassword(String email, String password);
    void modifyMemberName(String email, String name);

    void deleteMember(String email);

}