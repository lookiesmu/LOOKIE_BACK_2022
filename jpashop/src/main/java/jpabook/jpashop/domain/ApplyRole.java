package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ApplyRole {
    @Id @GeneratedValue
    @Column(name = "applyrole_id")
    private Long id;

    @OneToOne(mappedBy = "applyrole", fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private QuestionAnswer answer;

    @OneToMany(mappedBy = "applyRole", cascade = CascadeType.ALL)
    private List<MyApplicationList> myApplicationLists = new ArrayList<>();
}
