package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "club_list")

public class clubList {

    @Id @GeneratedValue
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "memberjoin_Id")
    private memberJoin member;

    @ManyToOne
    @JoinColumn(name = "clubs_Id")
    private Clubs clubs;


}
