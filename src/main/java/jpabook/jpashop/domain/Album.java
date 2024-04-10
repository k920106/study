package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Album extends Item {
    private String artist;

    private String etc;
}
