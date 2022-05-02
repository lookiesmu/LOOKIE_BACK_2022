package lookieBE.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Apply {

    @Id @GeneratedValue
    @Column(name = "apply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계
    @JoinColumn(name = "apply_member_id") // 매핑을 무엇으로 할 것인지
    private ApplyMember applyMember;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private Application application;

    private LocalDateTime applyDate; // 가입신청 시간

    //==연관관계 메서드==//
    public void setMember(ApplyMember applyMember){
        this.applyMember = applyMember;
        applyMember.getApply().add(this);
    }

    public void setApplication(Application application){
        this.application = application;
        application.setApply(this);
    }
}
