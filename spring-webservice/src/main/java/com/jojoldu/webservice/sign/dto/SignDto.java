package com.jojoldu.webservice.sign.dto;

import com.jojoldu.webservice.sign.entity.Sign;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SignDto {

    private Long id;
    private String userid;
    private String pw;

    @Builder
    public SignDto(Long id, String userid, String pw) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
    }

    public SignDto(Sign entity) {
        id = entity.getId();
        userid = entity.getUserid();
        pw = entity.getPw();
    }

    public Sign toEntity() {
        return Sign.builder()
                            .id(id)
                            .userid(userid)
                            .pw(pw)
                            .build();
    }

}
