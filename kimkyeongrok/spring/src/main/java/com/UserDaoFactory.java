package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class UserDaoFactory {
    @Bean
    public DataSource getLocalDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Bean
    public DataSource getPrdDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_prd");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

    @Bean
    public UserDao localUserDao() {
        DataSource dataSource = getLocalDataSource();
        UserDao dao = new UserDao();
        dao.setDataSource(dataSource);
        return dao;
    }

    @Bean
    public UserDao prdUserDao() {
        DataSource dataSource = getPrdDataSource();
        UserDao dao = new UserDao();
        dao.setDataSource(dataSource);
        return dao;
    }
}
