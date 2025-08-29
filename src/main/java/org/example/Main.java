package org.example;

import org.example.model.User;
import org.example.repository.UserDAO;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();


        System.out.println("--- YENİ KULLANICILAR EKLENİYOR ---");
        userDAO.save(new User("Ahmet Yılmaz", "ahmet@mail.com"));
        userDAO.save(new User("Zeynep Kaya", "zeynep@mail.com"));
        userDAO.save(new User("Mustafa Demir", "mustafa@mail.com"));


        System.out.println("\n--- TÜM KULLANICILAR ---");
        List<User> allUsers = userDAO.findAll();
        allUsers.forEach(System.out::println);


        System.out.println("\n--- KULLANICI GÜNCELLENİYOR ---");
        User userToUpdate = userDAO.findById(2);
        if (userToUpdate != null) {
            userToUpdate.setEmail("zeynep.kaya@newmail.com");
            userDAO.update(userToUpdate);
            System.out.println("Güncellenmiş Kullanıcı: " + userDAO.findById(2));
        }

        System.out.println("\n--- KULLANICI SİLİNİYOR ---");
        userDAO.delete(1);

        System.out.println("\n--- SON KULLANICI LİSTESİ ---");
        List<User> finalUsers = userDAO.findAll();
        finalUsers.forEach(System.out::println);

        org.example.util.HibernateUtil.shutdown();
    }
}

