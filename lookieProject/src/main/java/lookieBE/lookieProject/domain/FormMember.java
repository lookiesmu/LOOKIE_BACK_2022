package lookieBE.lookieProject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class FormMember{
    @Id @GeneratedValue
    @Column(name = "form_member_id")
    private Long id;

    private String email;
    private String password;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToMany(mappedBy = "formMember", cascade = CascadeType.ALL)
    private List<Form> form = new ArrayList<>();
}
