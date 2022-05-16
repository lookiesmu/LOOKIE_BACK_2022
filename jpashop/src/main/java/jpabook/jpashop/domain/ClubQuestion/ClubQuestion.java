package jpabook.jpashop.domain.ClubQuestion;


import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.MadeRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class ClubQuestion {

    @Id @GeneratedValue
    @Column(name = "ClubQuestion_id")
    private Long id;

    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maderole_id")
    private MadeRole madeRole;

    @ManyToMany(mappedBy = "clubQuestions")
    private List<Category> categories = new ArrayList<>();

}
