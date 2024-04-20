package com.jojoldu.webservice.sign.entity;

import com.jojoldu.webservice.BaseTimeEntity;
import com.jojoldu.webservice.board.entity.Board;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
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

    @OneToMany(mappedBy = "sign", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Sign(Long id, String userid, String pw, List<Board> boards) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
        this.boards = boards;
    }
}


