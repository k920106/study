package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Book extends Item {
    private String author;

    private String isbn;
}
