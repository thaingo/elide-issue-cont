package example.models;

import com.yahoo.elide.annotation.Include;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Include
public class Sample extends BaseEntity {

    @Id
    private String id;
    private String email = "";

    @Embedded
    private Address address;

}
