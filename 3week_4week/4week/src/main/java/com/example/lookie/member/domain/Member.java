package com.example.lookie.member.domain;


import com.example.lookie.grouprequest.domain.GroupRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member",orphanRemoval = true)
    private List<GroupRequest> groupRequestList = new ArrayList<>();


    public void addGroupRequest(GroupRequest groupRequest){
        this.groupRequestList.add(groupRequest);
    }


    public static Member createUserMember(String email, String password, String name, Address address){
        Member member = new Member();
        member.name=name;
        member.email=email;
        member.password=password;
        member.role=Role.ROLE_USER;
        member.address=address;
        return member;
    }


    public static Member createAdminMember(String email, String password, String name, Address address){
        Member member = new Member();
        member.name=name;
        member.email=email;
        member.password=password;
        member.role=Role.ROLE_ADMIN;
        member.address=address;
        return member;
    }

    public Long signup(String email, String password, Role role, String name, Address address){
        Member member = new Member();
        return member.getId();
    };

    public Member checkRoleAndCreateMember(String email, String password, Role role, String name, Address address){
        Member member = new Member();
        if(role == Role.ROLE_ADMIN) {
            member = member.createAdminMember(email, password, name, address);
        }
        else if(role == Role.ROLE_USER){
            member = member.createUserMember(email, password, name, address);
        }
        return member;
    }



    public void modifyPassword(String password) {
        this.password = password;
    }

    public void modifyName(String name) {
        this.name = name;
    }
}
