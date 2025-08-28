package org.example.repository;

import config.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserRepository {


    public void addUser(String name, String email) {

        String insertSQL = "INSERT INTO users(name, email) VALUES (?, ?)";


        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);

            preparedStatement.setString(2, email);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Kullanıcı başarıyla eklendi: " + name);
            }

        } catch (SQLException e) {
            System.err.println("Kullanıcı eklenirken bir hata oluştu: " + e.getMessage());
        }
    }
}
