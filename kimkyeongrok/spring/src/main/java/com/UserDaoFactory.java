package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {
    @Bean
    public UserDao localUserDao() {
        ConnectionMaker connectionMaker = new LocalConnectionMaker();
        UserDao dao = new UserDao();
        dao.setConnectionMaker(connectionMaker);
        return dao;
    }

    @Bean
    public UserDao prdUserDao() {
        ConnectionMaker connectionMaker = new PrdConnectionMaker();
        UserDao dao = new UserDao();
        dao.setConnectionMaker(connectionMaker);
        return dao;
    }
}
