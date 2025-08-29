package org.example;

import org.example.model.User;
import org.example.repository.UserDAO;
import org.example.util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        System.out.println("--- Yeni Kullanıcılar Ekleniyor ---");
        userDAO.save(new User("Ahmet", "ahmet@mail.com"));
        userDAO.save(new User("Ayşe", "ayse@mail.com"));
        userDAO.save(new User("Berkan", "berkan@mail.com"));

        System.out.println("\n--- Veritabanındaki Güncel Kullanıcı Listesi ---");
        List<User> allUsers = userDAO.findAll();
        printUsers(allUsers);

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

