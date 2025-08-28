package org.example;

import model.User;
import org.example.repository.UserRepository;
import org.example.repository.UserTableManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserTableManager tableManager = new UserTableManager();
        UserRepository userRepository = new UserRepository();

        // --- HAZIRLIK ---
        // Temiz bir başlangıç için tabloyu yeniden oluştur ve örnek verileri ekle.
        tableManager.recreateUserTable();
        userRepository.addUser("Ali", "ali@mail.com");       // ID=1
        userRepository.addUser("Veli", "veli@mail.com");     // ID=2
        userRepository.addUser("Ayşe", "ayse@mail.com");   // ID=3

        // --- İŞLEM ÖNCESİ DURUM ---
        System.out.println("\n--- İşlem Öncesi Kullanıcı Listesi ---");
        printUsers(userRepository.getAllUsers());

        // --- GÜNCELLEME İŞLEMİ ---
        System.out.println("\n--- Güncelleme İşlemi ---");
        // ID'si 2 olan Veli'nin adını "Veli Yılmaz" olarak güncelleyelim.
        userRepository.updateUserName(2, "Veli Yılmaz");

        // --- GÜNCELLEME SONRASI DURUM ---
        System.out.println("\n--- Güncelleme Sonrası Kullanıcı Listesi ---");
        printUsers(userRepository.getAllUsers());

        // --- SİLME İŞLEMİ ---
        System.out.println("\n--- Silme İşlemi ---");
        // ID'si 1 olan Ali'yi silelim.
        userRepository.deleteUser(1);

        // --- SİLME SONRASI DURUM ---
        System.out.println("\n--- Silme Sonrası Kullanıcı Listesi ---");
        printUsers(userRepository.getAllUsers());
    }

    /**
     * Kullanıcı listesini konsola formatlı bir şekilde yazdıran yardımcı metot.
     * @param users Yazdırılacak kullanıcı listesi.
     */
    private static void printUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("Veritabanında hiç kullanıcı bulunamadı.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
}