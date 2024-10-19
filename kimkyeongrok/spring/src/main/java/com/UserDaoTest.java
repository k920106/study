package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context;
        context = new AnnotationConfigApplicationContext(UserDaoFactory.class);
        UserDao dao = context.getBean("localUserDao", UserDao.class);

        String environment = "prd";
        if (environment.equals("prd")) {
            dao = context.getBean("prdUserDao", UserDao.class);
        }

        User user = new User();
        user.setId("1");
        user.setPassword("kms1234");
        user.setName("kms");
        dao.add(user);

        User selectedUser = dao.get("1");
        System.out.println("id: " + selectedUser.getId());
        System.out.println("password: " + selectedUser.getPassword());
        System.out.println("name: " + selectedUser.getName());
    }
}
