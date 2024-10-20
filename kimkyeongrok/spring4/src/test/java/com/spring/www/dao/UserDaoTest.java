package com.spring.www.dao;

import com.spring.www.domain.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        String id = "hgd10";

        User user = new User();
        user.setId(id);
        user.setPassword("hkd1234");
        user.setName("honggildong");

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.userDao();
        userDao.add(user);

        User result = userDao.get(id);
        System.out.println(result.getId());
        System.out.println(result.getPassword());
        System.out.println(result.getName());

        assertEquals(id, result.getId());
    }
}
