package com.spring.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        ApplicationContext context = SpringApplication.run(Application.class, args);
//        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
//
//        User user = new User();
//        //user.setId("2");
//        user.setId("3");
//        user.setPassword("password1234");
//        user.setName("name1234");
//
//        userRepository.save(user);
//
//        User newUser = new User();
//        //newUser.setId("2");
//        newUser.setId("3");
//        Example<User> userExample = Example.of(newUser);
//        User selectedUser = userRepository.findOne(userExample).orElse(null);
//        System.out.println(selectedUser.getId());
//        System.out.println(selectedUser.getPassword());
//        System.out.println(selectedUser.getName());
//
//        if (selectedUser != null) {
//            userRepository.delete(selectedUser);
//        }
//
//        long count = userRepository.count();
//        System.out.println("count = " + count);
    }
}
