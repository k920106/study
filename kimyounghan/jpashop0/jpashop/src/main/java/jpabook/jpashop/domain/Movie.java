package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Movie extends Item {
    private String director;

    private String actor;
}
