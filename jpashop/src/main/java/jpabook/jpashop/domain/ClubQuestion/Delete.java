package jpabook.jpashop.domain.ClubQuestion;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
@Getter @Setter
public class Delete extends ClubQuestion {

    private String delete;
}
