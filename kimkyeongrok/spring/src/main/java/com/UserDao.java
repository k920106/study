package com;

import java.sql.*;

public abstract class UserDao {
    abstract Connection getConnection() throws SQLException;

    public void add(User user) throws SQLException {
        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(id, password, name) VALUES(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public User get(String id) throws SQLException {
        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));

        rs.close();
        ps.close();
        con.close();

        return user;
    }

    public static void main(String[] args) throws SQLException {
        //UserDao dao = new LocalUserDao();
        UserDao dao = new PrdUserDao();
        User user = new User();
        user.setId("1234");
        user.setPassword("kms1234");
        user.setName("kms");
        dao.add(user);

        User selectedUser = dao.get("1234");
        System.out.println("id: " + selectedUser.getId());
        System.out.println("password: " + selectedUser.getPassword());
        System.out.println("name: " + selectedUser.getName());
    }
}
