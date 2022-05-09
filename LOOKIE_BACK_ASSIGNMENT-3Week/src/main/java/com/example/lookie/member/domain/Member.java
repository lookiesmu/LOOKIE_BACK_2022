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

    @OneToMany(mappedBy = "member",orphanRemoval = true) // orphanRemoval → 부모 엔티티가 삭제되면 자식 엔티티도 삭제.
    // 부모 엔티티에서 자식 엔티티를 제거 가능.
    private List<GroupRequest> groupRequestList = new ArrayList<>();


    // 연관관계 메서드
    public void addGroupRequest(GroupRequest groupRequest){
        this.groupRequestList.add(groupRequest);
    }


    // User 를 생성하는 메서드
    public static Member createUserMember(String email, String password, String name, Address address){
        Member member = new Member();
        member.name=name;
        member.email=email;
        member.password=password;
        member.role=Role.ROLE_USER;
        member.address=address;
        return member;
    }


    // 동아리 관리자를 생성하는 메서드
    public static Member createAdminMember(String email, String password, String name, Address address){
        Member member = new Member();
        member.name=name;
        member.email=email;
        member.password=password;
        member.role=Role.ROLE_ADMIN;
        member.address=address;
        return member;
    }

    public void modifyPassword(String password){
        this.password = password;
    }

    public void modifyName(String name){
        this.name = name;
    }
}
