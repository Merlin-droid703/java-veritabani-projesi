package org.example;

import config.DatabaseConfig;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DatabaseConfig.getConnection();

            if (connection != null && !connection.isClosed()) {
                System.out.println("Connected!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while checking the connection: " + e.getMessage());
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    System.err.println("Failed to close the connection: " + e.getMessage());
                }
            }
        }
    }
}
