package com.example.assignment1gc200624860;

import java.sql.*;

public class DBConnection {
    private static final String URL  = "jdbc:mysql://172.31.22.43:3306/Afia200624860";
    private static final String USER = "Afia200624860";
    private static final String PASS = "icdXxdgaFh";
    private static Connection conn;
    private DBConnection() {}
    public static Connection getConnection() throws SQLException {
        if (conn==null||conn.isClosed())
            conn=DriverManager.getConnection(URL,USER,PASS);
        return conn;
    }
}

