package example.models;

import com.yahoo.elide.annotation.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
@Getter
@Setter
public class Entity1 extends BaseEntity {

    @Id
    private String id;

}
