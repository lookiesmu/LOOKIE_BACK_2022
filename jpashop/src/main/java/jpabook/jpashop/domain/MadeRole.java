package jpabook.jpashop.domain;


import jpabook.jpashop.domain.ClubQuestion.ClubQuestion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class MadeRole {
    @Id @GeneratedValue
    @Column(name = "maderole_id")
    private Long id;

    @OneToOne(mappedBy = "maderole", fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    private MadeClub madeclub;

    @Enumerated(EnumType.STRING)
    private NotApplyClub notApplyClub;

    @OneToMany(mappedBy = "madeRole", cascade = CascadeType.ALL)
    private List<ClubQuestion> clubQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "madeRole", cascade = CascadeType.ALL)
    private List<ClubApplicationList> clubApplicationLists = new ArrayList<>();
}
