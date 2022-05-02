package lookie.project.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Question {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToMany
    @JoinTable(name = "question_answer",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "applicationAnswer_id"))
    private List<ApplicationAnswer> applicationAnswers = new ArrayList<>();

    //==연관 관계 메서드==//
    public void setClub(Club club) {
        this.club = club;
        club.getQuestions().add(this);
    }
}
