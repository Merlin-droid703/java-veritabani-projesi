package org.example.repository;

import config.DatabaseConfig;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void save(User user) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            System.out.println("Kullanıcı başarıyla kaydedildi: " + user.getName());
        } catch (SQLException e) {
            System.err.println("Kullanıcı kaydedilirken hata oluştu: " + e.getMessage());
        }
    }


    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id";
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcılar listelenirken hata oluştu: " + e.getMessage());
        }
        return users;
    }

    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("ID=" + user.getId() + " olan kullanıcı güncellendi.");
            } else {
                System.out.println("ID=" + user.getId() + " olan kullanıcı bulunamadı.");
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcı güncellenirken hata oluştu: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("ID=" + id + " olan kullanıcı silindi.");
            } else {
                System.out.println("ID=" + id + " olan kullanıcı bulunamadı.");
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcı silinirken hata oluştu: " + e.getMessage());
        }
    }
}

