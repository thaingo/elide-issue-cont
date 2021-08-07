package example.models;

import com.yahoo.elide.annotation.Include;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Include
public class Entity2 extends BaseEntity implements HasOwner, HasXyz, HasAbc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String owner;
    private String xyz;
    private String abc;

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public String getXyz() {
        return this.xyz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setXyz(String creator) {
        this.xyz = creator;
    }

    @Override
    public String getAbc() {
        return this.abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }
}
