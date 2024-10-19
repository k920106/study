package com.spring.www.component;

import com.spring.www.domain.User;
import com.spring.www.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired private UserRepository userRepository;

    @Scheduled(fixedRate = 1000)
    public void printHello() {
        //UserRepository userRepository = context.getBean("userRepository", UserRepository.class);

        User user = new User();
        user.setId("1");
        user.setPassword("password1234");
        user.setName("name1234");

        userRepository.save(user);

        User newUser = new User();
        newUser.setId("1");
        Example<User> userExample = Example.of(newUser);
        User selectedUser = userRepository.findOne(userExample).orElse(null);
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getPassword());
        System.out.println(selectedUser.getName());

        if (selectedUser != null) {
            userRepository.delete(selectedUser);
        }

        long count = userRepository.count();
        System.out.println("count = " + count);
    }
}
