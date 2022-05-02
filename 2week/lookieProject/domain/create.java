package lookieProject.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;
import lookieProject.lookieProject.domain.application.Application;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "create")
@Getter @Setter
public class create {

    @Id @GeneratedValue
    @Column(name = "create_id")
    private Long id;

    private String name;
    private String explain;


    @ManyToOne
    @JoinColumn(name = "createMember_id")
    private createMember member;

    @OneToOne(mappedBy = "club_id")
    private List<Application> Applications = new ArrayList<>();

}
