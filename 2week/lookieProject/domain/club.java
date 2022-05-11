package lookieProject.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;
import lookieProject.lookieProject.domain.application.Application;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class club {

    @Id @GeneratedValue
    @Column(name = "club_id")
    private Long id;

    private String name;
    private String explain;


    @OneToOne
    @JoinColumn(name="apply_id")
    private apply apply;

    @OneToOne
    @JoinColumn(name = "create_id")
    private create Create;

    private List<Application> Applications = new ArrayList<>();

}
