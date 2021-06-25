package example.models;

import com.yahoo.elide.annotation.Include;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
public class Entity1 extends BaseEntity {

    @Id
    private String id;

    private String name;
}
