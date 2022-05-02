package lookieProject.lookieProject.domain.application;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Application {

    @Id @GeneratedValue
    @Column(name = "Application_id")
    private Long id;

    private String name;
    private String answer;

    @ManyToMany(mappedBy = "apply_id")
    private List<Application> Applications = new ArrayList<Application>();

}