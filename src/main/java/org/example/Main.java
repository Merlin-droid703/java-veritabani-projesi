package org.example;

import model.User;
import org.example.repository.UserDAO;
import org.example.repository.UserTableManager;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserTableManager tableManager = new UserTableManager();
        UserDAO userDAO = new UserDAO();


        tableManager.recreateUserTable();
        System.out.println("\n--- Yeni Kullanıcılar Kaydediliyor ---");
        userDAO.save(new User(0, "Ali", "ali@mail.com"));
        userDAO.save(new User(0, "Veli", "veli@mail.com"));
        userDAO.save(new User(0, "Ayşe", "ayse@mail.com"));


        System.out.println("\n========== VERİTABANINDAKİ KULLANICILAR ==========");
        printUsers(userDAO.findAll());
    }


    private static void printUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("-> Veritabanında hiç kullanıcı bulunamadı.");
        } else {
            // Lambda ve forEach ile daha modern bir yazdırma yöntemi
            users.forEach(user -> System.out.println("-> " + user));
        }
    }
}

