package org.example;

import org.example.model.User;
import org.example.service.UserService;
import org.example.util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        System.out.println("--- Yeni Kullanıcılar Ekleniyor ---");
        userService.addUser("Ahmet", "ahmet@mail.com");
        userService.addUser("Ayşe", "ayse@mail.com");
        userService.addUser("Berkan", "berkan@mail.com");

        // Tekrarlanan kaydı bilerek ekleyelim
        System.out.println("\n--- Tekrarlanan Kayıt Ekleniyor ---");
        userService.addUser("Ahmet Kopya", "ahmet@mail.com");

        System.out.println("\n--- Başlangıç Listesi (Tekrarlanan Kayıt ile Birlikte) ---");
        printUsers(userService.getAllUsers());

        // Tekrarlanan kayıtları temizle
        System.out.println("\n--- Tekrarlanan Kayıtlar Kontrol Edilip Siliniyor ---");
        userService.removeDuplicateUsers();

        System.out.println("\n--- Temizlenmiş Liste ---");
        printUsers(userService.getAllUsers());

        System.out.println("\n--- ID'si 2 Olan Kullanıcı Güncelleniyor ---");
        User userToUpdate = userService.getUserById(2);
        if (userToUpdate != null) {
            userService.updateUser(userToUpdate.getId(), "Ayşe Yılmaz", "ayse.yilmaz@mail.com");
            System.out.println("Kullanıcı güncellendi.");
        } else {
            System.out.println("ID'si 2 olan kullanıcı güncellenmek için bulunamadı.");
        }

        System.out.println("\n--- Güncelleme Sonrası Liste ---");
        printUsers(userService.getAllUsers());

        HibernateUtil.shutdown();
    }

    private static void printUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("-> Veritabanında hiç kullanıcı bulunamadı.");
        } else {
            users.forEach(user -> System.out.println("-> " + user));
        }
    }
}
