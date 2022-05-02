package lookieproject.backend1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ClubApplication {
    @Id @GeneratedValue
    @Column(name = "clubapplication_id")
    private Long id;

    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "madeclub_id")
    private MadeClub madeClub;
}
