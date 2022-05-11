package lookieproject.backend1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class MadeClub {
    @Id @GeneratedValue
    @Column(name = "madeclub_id")
    private Long id;

    @OneToOne(mappedBy = "madeClub", fetch = FetchType.LAZY)
    private Member member;

    private String name;

    private String explanation;

    @OneToMany(mappedBy = "madeClub", cascade = CascadeType.ALL)
    private List<ClubApplication> clubApplications= new ArrayList<>();

    @OneToMany(mappedBy = "madeClub", cascade = CascadeType.ALL)
    private List<CheckApplicationList> checkApplicationListLists= new ArrayList<>();
}
