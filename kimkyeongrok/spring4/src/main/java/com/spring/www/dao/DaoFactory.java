package com.spring.www.dao;

public class DaoFactory {
    public UserDao userDao() {
        //NConnectionMaker nConnectionMaker = new NConnectionMaker();
        //UserDao userDao = new UserDao(nConnectionMaker);
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    private static NConnectionMaker connectionMaker() {
        return new NConnectionMaker();
    }
}
