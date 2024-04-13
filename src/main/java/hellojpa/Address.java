package hellojpa;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Embeddable
public class Address {
    private String city;

    private String street;

    private String zipcode;
}
