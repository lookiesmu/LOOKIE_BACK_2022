package lookieBE.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Application {

    @Id @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    private Apply apply;

    private String questions;
    private String answers;

}
