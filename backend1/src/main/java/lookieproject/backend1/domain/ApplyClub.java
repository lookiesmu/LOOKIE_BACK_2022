package lookieproject.backend1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ApplyClub {
    @Id @GeneratedValue
    @Column(name = "applyclub_id")
    private Long id;

    @OneToOne(mappedBy = "applyClub",fetch = FetchType.LAZY)
    private Member member;

    private String answer;

    @OneToMany(mappedBy = "applyClub",cascade = CascadeType.ALL)
    private List<MyApplicationList> myApplicationListList =new ArrayList<>();
}
