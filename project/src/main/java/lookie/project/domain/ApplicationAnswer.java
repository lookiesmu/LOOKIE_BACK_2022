package lookie.project.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class ApplicationAnswer {

    @Id @GeneratedValue
    @Column(name = "applicationAnswer_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "memberApply_id")
    private MemberApply memberApply;

    @ManyToMany(mappedBy = "applicationAnswers")
    private List<Question> questions = new ArrayList<>();

    //==연관관계 메서드==//
    public void setMemberApply(MemberApply memberApply) {
        this.memberApply = memberApply;
    }
}
