package com.spring.www.dao;

import com.spring.www.domain.User;
import com.spring.www.factories.DaoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
public class UserDaoTest {
    @Autowired private UserDao userDaoMySql;

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setId(3);
        user.setName("test");
        user.setPassword("test1234");

        userDaoMySql.add(user);
    }
}
