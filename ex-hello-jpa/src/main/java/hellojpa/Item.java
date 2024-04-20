package hellojpa;

import jakarta.persistence.*;
import lombok.*;

@DiscriminatorColumn
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
//public class Item {
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
