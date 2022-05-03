package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter

public class createClub {
    @Id @GeneratedValue
    @Column(name = "createclub_Id")
    private Long Id;

    private String name;

    private String explain;

    private String questions;

    @OneToOne
    @JoinColumn(name = "membercreate_Id")
    private memberCreate create;

    @Enumerated(EnumType.STRING)
    private questionEdit questionedit;




}
