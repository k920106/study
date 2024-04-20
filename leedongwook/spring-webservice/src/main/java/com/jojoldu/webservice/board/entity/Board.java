package com.jojoldu.webservice.board.entity;

import com.jojoldu.webservice.BaseTimeEntity;
import com.jojoldu.webservice.sign.entity.Sign;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String content;

    @ManyToOne
    private Sign sign;

    @Builder
    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

