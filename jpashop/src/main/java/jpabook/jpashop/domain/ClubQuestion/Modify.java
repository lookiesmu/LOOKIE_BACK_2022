package jpabook.jpashop.domain.ClubQuestion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
public class Modify extends ClubQuestion {
    private  String modify;
}
