package org.example.repository;

import org.example.config.DatabaseConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTableManager {


    public void recreateUserTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS users";


        String createTableSQL = "CREATE TABLE users (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) NOT NULL UNIQUE" +
                ")";

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            System.out.println("'users' tablosu (varsa) siliniyor...");
            statement.execute(dropTableSQL);
            System.out.println("'users' tablosu oluşturuluyor...");
            statement.execute(createTableSQL);
            System.out.println("'users' tablosu başarıyla oluşturuldu!");

        } catch (SQLException e) {
            System.err.println("İşlem sırasında bir hata oluştu: " + e.getMessage());
        }
    }
}
