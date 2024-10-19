package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {
    @Bean
    public UserDao localUserDao() {
        ConnectionMaker connectionMaker = new LocalConnectionMaker();
        return new UserDao(connectionMaker);
    }

    @Bean
    public UserDao prdUserDao() {
        ConnectionMaker connectionMaker = new PrdConnectionMaker();
        return new UserDao(connectionMaker);
    }
}
