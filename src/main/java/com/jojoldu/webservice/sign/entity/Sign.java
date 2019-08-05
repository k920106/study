package com.jojoldu.webservice.sign.entity;

import com.jojoldu.webservice.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Sign extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(length = 20, nullable = true)
    @Column(length = 20)
    private String userid;

    //@Column(length = 20, nullable = true)
    @Column(length = 20)
    private String pw;

    @Builder
    public Sign(Long id, String userid, String pw) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
    }

}
