package com.jojoldu.webservice.board.entity;

import com.jojoldu.webservice.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String comment;

    @Column(length = 100, nullable = false)
    private String listNum;

    @Builder
    public Comment(Long id, String comment, String listNum) {
        this.id = id;
        this.comment = comment;
        this.listNum = listNum;
    }

}
