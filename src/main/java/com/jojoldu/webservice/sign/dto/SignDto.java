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

    private String userid;
    private String pw;

    @Builder
    public SignDto(String userid, String pw) {
        this.userid = userid;
        this.pw = pw;
    }

    public SignDto(Sign entity) {
        userid = entity.getUserid();
        pw = entity.getPw();
    }

    public Sign toEntity() {
        return Sign.builder()
                .userid(userid)
                .pw(pw)
                .build();
    }

}
