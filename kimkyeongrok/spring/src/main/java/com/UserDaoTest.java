package com;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException {
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        UserDao dao = userDaoFactory.getLocalUserDao();

        String environment = "prd";
        if (environment.equals("prd")) {
            dao = userDaoFactory.getPrdUserDao();
        }

        User user = new User();
        user.setId("12345678");
        user.setPassword("kms1234");
        user.setName("kms");
        dao.add(user);

        User selectedUser = dao.get("12345678");
        System.out.println("id: " + selectedUser.getId());
        System.out.println("password: " + selectedUser.getPassword());
        System.out.println("name: " + selectedUser.getName());
    }
}
