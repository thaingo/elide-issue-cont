package example.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@ToString
public class Address {

    private String city;
    private String country;
}
