package lookieProject.lookieProject.domain;


import lombok.Getter;
import lombok.Setter;
import lookieProject.lookieProject.domain.application.Application;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "apply")
@Getter @Setter
public class apply {


    @Id @GeneratedValue
    @Column(name = "apply_id")
    private Long id;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "applymember_id")
    private applyMember member;

    @OneToMany(mappedBy = "club_id")
    private List<Application> Applications = new ArrayList<>();

}