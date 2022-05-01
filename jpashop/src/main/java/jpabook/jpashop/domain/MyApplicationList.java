package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MyApplicationList {
    @Id @GeneratedValue
    @Column(name = "myapplicationlist_id" )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applyrole_id")
    private ApplyRole applyRole;
}
