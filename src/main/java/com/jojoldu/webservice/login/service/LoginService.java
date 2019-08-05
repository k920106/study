package com.jojoldu.webservice.login.service;

import com.jojoldu.webservice.sign.dto.SignDto;
import com.jojoldu.webservice.sign.entity.Sign;
import com.jojoldu.webservice.sign.repository.SignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class LoginService {

    private SignRepository signRepository;

    // Controller - 로그인 체크
    @Transactional(readOnly = true)
    public SignDto findByUserid(SignDto signDto) throws Exception {
        
        Sign loginid = signRepository.findByUserid(signDto.getUserid());

        // 만약 아이디를 잘못 입력하면?
        if(loginid == null){
            return new SignDto();
        }else{
            return new SignDto(loginid);
        }
    }

    // Controller - 비밀번호 체크
    @Transactional(readOnly = true)
    public SignDto findByUseridAndPw(SignDto signDto) throws Exception {

        Sign loginidpw = signRepository.findByUseridAndPw(signDto.getUserid(), signDto.getPw());

        // 만약 비밀번호를 잘못 입력하면?
        if(loginidpw == null) {
            return new SignDto();
        }else{
            return new SignDto(loginidpw);
        }

    }

}
