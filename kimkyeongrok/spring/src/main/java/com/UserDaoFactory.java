package com;

public class UserDaoFactory {
    public UserDao getLocalUserDao() {
        ConnectionMaker connectionMaker = new LocalConnectionMaker();
        return new UserDao(connectionMaker);
    }

    public UserDao getPrdUserDao() {
        ConnectionMaker connectionMaker = new PrdConnectionMaker();
        return new UserDao(connectionMaker);
    }
}
