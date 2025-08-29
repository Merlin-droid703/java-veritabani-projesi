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

        System.out.println("\n--- Veritabanındaki Tüm Kullanıcılar ---");
        List<User> allUsers = userDAO.findAll();
        printUsers(allUsers);

        System.out.println("\n--- ID'si 2 Olan Kullanıcı Aranıyor ---");
        User foundUser = userDAO.findById(2);
        if (foundUser != null) {
            System.out.println("Bulunan Kullanıcı: " + foundUser);
        } else {
            System.out.println("ID'si 2 olan kullanıcı bulunamadı.");
        }

        System.out.println("\n--- Var olmayan bir kullanıcı aranıyor (ID=99) ---");
        User notFoundUser = userDAO.findById(99);
        if (notFoundUser == null) {
            System.out.println("ID'si 99 olan kullanıcı beklendiği gibi bulunamadı.");
        }

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
