package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class memberJoin extends Member1 {

    @Id @GeneratedValue
    @Column(name = "memberjoin_Id")

    @OneToMany
    @JoinColumn(name = "member_Id")
    private Member1 member;

    @OneToMany(mappedBy = "member")
    private List<clubList> clublist = new ArrayList<>();

}
