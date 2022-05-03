package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clubs_joined")
@Getter @Setter

public class Clubs {

    @Id @GeneratedValue
    @Column (name = "clubs_Id")
    private Long Id;

    @ManyToOne
    @JoinColumn (name = "memberjoin_Id")
    private List<memberCreate> participants;

    @OneToOne
    @JoinColumn (name = "createclub_Id")
    private createClub clubmade;

}
