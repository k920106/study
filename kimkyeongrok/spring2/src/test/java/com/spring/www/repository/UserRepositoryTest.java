package com.spring.www.repository;

import com.spring.www.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void save() {
        //User user = new User("kms", "kms1234", "hongildong");
        //userRepository.save(user);
        User user = new User();
        user.setId("kms");
        Example<User> userExample = Example.of(user);
        User selectedUser = userRepository.findOne(userExample).orElse(null);
        assertEquals("kms", selectedUser.getId());
    }
}
