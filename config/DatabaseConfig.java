package com.campusflow.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://mysql-305d2cee-nagadurganalla79-116.f.aivencloud.com:27385/defaultdb?sslMode=REQUIRED";
    private static final String USER = "avnadmin";
   private static final String PASSWORD = "YOUR_AIVEN_PASSWORD_HERE";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}