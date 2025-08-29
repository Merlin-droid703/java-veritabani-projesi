package org.example;

import org.example.model.User;
import org.example.repository.UserDAO;
import org.example.util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        System.out.println("--- Yeni Kullanıcılar Ekleniyor ---");
        userDAO.save(new User("Ahmet", "ahmet@mail.com"));
        userDAO.save(new User("Ayşe", "ayse@mail.com"));
        userDAO.save(new User("Berkan", "berkan@mail.com"));
        // Tekrarlanan kaydı bilerek ekleyelim
        System.out.println("\n--- Tekrarlanan Kayıt Ekleniyor ---");
        userDAO.save(new User("Ahmet Kopya", "ahmet@mail.com"));


        System.out.println("\n--- Başlangıç Listesi (Tekrarlanan Kayıt ile Birlikte) ---");
        printUsers(userDAO.findAll());

        // ==================== TEKRARLANAN KAYIT SİLME İŞLEMİ ====================
        System.out.println("\n--- Tekrarlanan Kayıtlar Kontrol Edilip Siliniyor ---");
        List<User> allUsers = userDAO.findAll();
        Set<String> uniqueEmails = new HashSet<>();
        List<User> duplicatesToDelete = new ArrayList<>();

        for (User user : allUsers) {
            if (!uniqueEmails.add(user.getEmail())) {
                // Bu e-posta daha önce eklendi, bu bir tekrar kaydıdır.
                duplicatesToDelete.add(user);
            }
        }

        if (!duplicatesToDelete.isEmpty()) {
            for (User duplicate : duplicatesToDelete) {
                System.out.println("Silinen tekrarlanan kayıt: " + duplicate);
                userDAO.delete(duplicate.getId());
            }
        } else {
            System.out.println("Tekrarlanan kayıt bulunamadı.");
        }


        System.out.println("\n--- Temizlenmiş Liste ---");
        printUsers(userDAO.findAll());


        System.out.println("\n--- ID'si 2 Olan Kullanıcı Güncelleniyor ---");
        User userToUpdate = userDAO.findById(2);
        if (userToUpdate != null) {
            userToUpdate.setName("Ayşe Yılmaz");
            userToUpdate.setEmail("ayse.yilmaz@mail.com");
            userDAO.update(userToUpdate);
            System.out.println("Kullanıcı güncellendi.");
        } else {
            System.out.println("ID'si 2 olan kullanıcı güncellenmek için bulunamadı.");
        }

        System.out.println("\n--- Güncelleme Sonrası Liste ---");
        printUsers(userDAO.findAll());


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