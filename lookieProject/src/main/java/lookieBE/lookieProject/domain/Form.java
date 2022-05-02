package lookieBE.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Form {
    @Id
    @GeneratedValue
    @Column(name = "form_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계
    @JoinColumn(name = "form_member_id") // 매핑을 무엇으로 할 것인지
    private FormMember formMember;

    private String name;
    private String explain;

    //==연관관계 메서드==//
    public void setMember(FormMember formMember){
        this.formMember = formMember;
        formMember.getForm().add(this);
    }
}
