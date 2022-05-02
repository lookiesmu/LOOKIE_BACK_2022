package lookieProject.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;
import lookieProject.lookieProject.domain.application.Application;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class createMember {

    @Id @GeneratedValue
    @Column(name = "createMember_id")
    private Long id;

    private String email;
    private String password;

    @Embedded
    private Address address;
    @OneToMany(mappedBy = "create_id")
    private List<Application> Applications = new ArrayList<>();
}

