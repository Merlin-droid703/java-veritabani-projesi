package org.example;

import model.User;
import org.example.repository.UserRepository;
import org.example.repository.UserTableManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserTableManager tableManager = new UserTableManager();
        UserRepository userRepository = new UserRepository();

        // --- ÖNEMLİ ---
        // Tabloyu bir kez oluşturduktan sonra, verilerin silinmemesi için
        // aşağıdaki satırı yorum satırı haline getirin (başına // ekleyin).
        // tableManager.recreateUserTable();

        // 2. Adım: Tabloya birkaç örnek kullanıcı ekle.
        System.out.println("\n--- Örnek Kullanıcılar Ekleniyor ---");
        userRepository.addUser("Ali", "ali@mail.com");
        userRepository.addUser("Veli", "veli@mail.com");
        userRepository.addUser("Ayşe", "ayse@mail.com");

        // 3. Adım: Tüm kullanıcıları veritabanından çek.
        System.out.println("\n--- Veritabanındaki Kullanıcılar Listeleniyor ---");
        List<User> allUsers = userRepository.getAllUsers();

        // 4. Adım: Çekilen kullanıcıları ekrana yazdır.
        if (allUsers.isEmpty()) {
            System.out.println("Veritabanında hiç kullanıcı bulunamadı.");
        } else {
            for (User user : allUsers) {
                System.out.println(user.toString());
            }
        }
    }
}
