package lookie.project.domain;

import lombok.Getter;
import lookie.project.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class MemberApply {

    @Id @GeneratedValue
    @Column(name = "memberApply_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToMany(mappedBy = "memberApply")
    private List<ApplicationAnswer> applicationAnswers = new ArrayList<>();

    private LocalDateTime dateAdded;  //지원 날짜

    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus;    //지원 상태 [APPROVE, ONGOING, REFER]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getMemberApplies().add(this);
    }

    public void setClub(Club club) {
        this.club = club;
        club.getMemberApplies().add(this);
    }

    public void addApplicationAnswer(ApplicationAnswer applicationAnswer) {
        applicationAnswers.add(applicationAnswer);
        applicationAnswer.setMemberApply(this);
    }
}
