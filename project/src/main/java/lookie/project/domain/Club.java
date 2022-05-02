package lookie.project.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Club {

    @Id @GeneratedValue
    @Column(name = "club_id")
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "club")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<MemberApply> memberApplies = new ArrayList<>();

    //==연관관계 메서드==//
    public void addQuestion(Question question) {
        questions.add(question);
        question.setClub(this);
    }

    public void addMemberApply(MemberApply memberApply) {
        memberApplies.add(memberApply);
        memberApply.setClub(this);
    }
}
