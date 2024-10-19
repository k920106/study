package com;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException {
        ConnectionMaker connectionMaker = new LocalConnectionMaker();

        String environment = "prd";
        if (environment.equals("prd")) {
            connectionMaker = new PrdConnectionMaker();
        }

        UserDao dao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("1234567");
        user.setPassword("kms1234");
        user.setName("kms");
        dao.add(user);

        User selectedUser = dao.get("1234567");
        System.out.println("id: " + selectedUser.getId());
        System.out.println("password: " + selectedUser.getPassword());
        System.out.println("name: " + selectedUser.getName());
    }
}
