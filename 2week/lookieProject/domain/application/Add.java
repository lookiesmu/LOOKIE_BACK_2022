package lookieProject.lookieProject.domain.application;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Add extends Application {

    private String name;
    private String answer;

}
