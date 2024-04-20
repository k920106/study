package com.jojoldu.webservice.sign.repository;

import com.jojoldu.webservice.sign.entity.Sign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<Sign, Long> {

    Sign findByUserid(String userid);

    Sign findByUseridAndPw(String userid, String pw);

}
