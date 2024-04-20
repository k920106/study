package com.jojoldu.webservice.domain.posts;

import static org.junit.Assert.assertTrue;

import com.jojoldu.webservice.sign.entity.Sign;
import com.jojoldu.webservice.sign.repository.SignRepository;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostsTest {

  @Autowired
  SignRepository signRepository;

  @Autowired
  EntityManager em;

  @Test
  public void 회원_조회(){
    signRepository.save(Sign.builder()
                            .id(1L)
                            .pw("1234")
                            .userid("kang")
                            .build());
    String password = signRepository.findByUserid("kang").getPw();
    assertTrue(password.equals("1234"));

    em.clear();
  }

  @Test
  public void 회원_수정(){
    signRepository.save(Sign.builder()
        .id(1L)
        .pw("1234")
        .userid("kang")
        .build());

    String password = signRepository.findByUserid("kang").getPw();
    assertTrue(password.equals("1234"));

    em.clear();
  }


}