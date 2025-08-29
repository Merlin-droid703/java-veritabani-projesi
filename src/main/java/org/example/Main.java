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


        System.out.println("\n========== İŞLEM ÖNCESİ LİSTE ==========");
        printUsers(userDAO.findAll());


        System.out.println("\n========== GÜNCELLEME YAPILIYOR ==========");

        userDAO.update(new User(2, "Veli Yılmaz", "veli@mail.com"));
        System.out.println("\n========== GÜNCELLEME SONRASI LİSTE ==========");
        printUsers(userDAO.findAll());


        System.out.println("\n========== SİLME YAPILIYOR ==========");

        userDAO.delete(1);
        System.out.println("\n========== SİLME SONRASI LİSTE ==========");
        printUsers(userDAO.findAll());
    }


    private static void printUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("-> Veritabanında hiç kullanıcı bulunamadı.");
        } else {

            users.forEach(user -> System.out.println("-> " + user));
        }
    }
}

