package com.jojoldu.webservice.sign.service;



import com.jojoldu.webservice.sign.dto.SignDto;
import com.jojoldu.webservice.sign.repository.SignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class SignService {

    private SignRepository signRepository;

    // Controller - 회원가입 등록
    @Transactional
    public Long save(SignDto signDto) {
        return signRepository.save(signDto.toEntity()).getId();
    }

}
