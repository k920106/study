package com.spring.www.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostgreConnectionMakerTest {
    @Test
    public void name() throws SQLException, ClassNotFoundException {
        ConnectionMaker c = new PostgreConnectionMaker();
        Connection connection = c.makeConnection();
        assertNotNull(c);
    }
}
