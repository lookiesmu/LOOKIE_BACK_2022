package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class memberCreate extends Member1{

    @Id @GeneratedValue
    @Column(name = "membercreate_Id")
    private Long id;

    @OneToOne
    private Member1 member;

    @Enumerated(EnumType.STRING)
    private memberAccess access;

    @OneToMany(mappedBy = "participants", cascade = CascadeType.ALL)
    private List<Clubs> joinedclub = new ArrayList<>();


}

