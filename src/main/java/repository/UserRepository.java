package org.example.repository;

import config.DatabaseConfig;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String selectSQL = "SELECT * FROM users ORDER BY id";

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(selectSQL)) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                User user = new User(id, name, email);

                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Kullanıcılar listelenirken bir hata oluştu: " + e.getMessage());
        }
        return users;
    }

    public void updateUserName(int id, String newName) {
        String updateSQL = "UPDATE users SET name = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("ID=" + id + " olan kullanıcı başarıyla güncellendi.");
            } else {
                System.out.println("ID=" + id + " olan bir kullanıcı bulunamadı.");
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcı güncellenirken bir hata oluştu: " + e.getMessage());
        }
    }


    public void deleteUser(int id) {
        String deleteSQL = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("ID=" + id + " olan kullanıcı başarıyla silindi.");
            } else {
                System.out.println("ID=" + id + " olan bir kullanıcı bulunamadı.");
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcı silinirken bir hata oluştu: " + e.getMessage());
        }
    }

}
