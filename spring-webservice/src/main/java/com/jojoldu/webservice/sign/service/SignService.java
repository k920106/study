package com.jojoldu.webservice.sign.service;


import com.jojoldu.webservice.sign.dto.SignDto;
import com.jojoldu.webservice.sign.entity.Sign;
import com.jojoldu.webservice.sign.repository.SignRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class SignService {

    private SignRepository signRepository;

    // 회원가입 - 등록
    @Transactional
    public Long save(SignDto signDto) {
        return signRepository.save(signDto.toEntity()).getId();
    }

    // 회원 리스트 - 전체조회
    public List<SignDto> findusers() {

        List<Sign> userlist = signRepository.findAll();

        List<SignDto> collect = userlist.stream()
            .map(SignDto::new)
            .collect(Collectors.toList());

        return collect;
    }

    public Sign findByUserId(String userid){
        return signRepository.findByUserid(userid);
    }

    // 회원 리스트 - 수정
//    public SignDto listEdit(SignDto signDto) {
//
//    }

}
