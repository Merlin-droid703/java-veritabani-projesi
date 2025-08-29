package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConfig {

    private static final String HOSTNAME = "localhost";

    private static final String PORT = "5432";

    private static final String DATABASE_NAME = "postgres";

    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "12";

    private static final String DB_URL = "jdbc:postgresql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {

            System.err.println("Veritabanı bağlantı hatası: " + e.getMessage());
        }
        return connection;
    }
}