package lookieBE.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ApplyMember{

    @Id @GeneratedValue
    @Column(name = "apply_member_id")
    private Long id;

    private String email;
    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "applyMember", cascade = CascadeType.ALL)
    private List<Apply> apply = new ArrayList<>();
}
