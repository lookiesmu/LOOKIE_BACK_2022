package lookieproject.backend1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CheckApplicationList {
    @Id @GeneratedValue
    @Column(name = "checkapplication_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "madeclub_id")
    private MadeClub madeClub;

    @Enumerated(EnumType.STRING)
    public ListStatus status; //승인 , 비승인
}
