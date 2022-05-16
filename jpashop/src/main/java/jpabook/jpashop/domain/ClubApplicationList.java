package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ClubApplicationList {
    @Id @GeneratedValue
    @Column(name = "ClubApplication_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MadeRole_id")
    private MadeRole madeRole;



}
