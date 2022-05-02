package lookieProject.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;
import lookieProject.lookieProject.domain.application.Application;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class applyMember {

    @Id @GeneratedValue
    @Column(name = "applyMember_id")
    private Long id;

    private String email;
    private String password;

    @Embedded
    private Address address;
    @OneToMany(mappedBy = "apply_id")
    private List<Application> Applications = new ArrayList<>();
}

