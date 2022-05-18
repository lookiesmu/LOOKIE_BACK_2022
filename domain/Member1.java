package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member1 {

    @Id @GeneratedValue
    @Column(name = "member_Id")
    private Long Id;

    private String email;

    private String password;

    private String address;


}
