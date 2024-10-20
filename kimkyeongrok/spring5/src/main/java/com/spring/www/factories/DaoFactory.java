package com.spring.www.factories;

import com.spring.www.dao.MysqlConnectionMaker;
import com.spring.www.dao.PostgreConnectionMaker;
import com.spring.www.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(new PostgreConnectionMaker());
    }

    @Bean
    public UserDao userDaoMySql() {
        return new UserDao(new MysqlConnectionMaker());
    }
}
