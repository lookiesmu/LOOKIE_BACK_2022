package lookie.project.domain.member;

import lombok.Getter;
import lombok.Setter;
import lookie.project.domain.MemberApply;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;  //멤버 역할 [FOUNDER, NEW]

    @OneToMany(mappedBy = "member")
    private List<MemberApply> memberApplies = new ArrayList<>();
}
