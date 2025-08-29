package org.example.service;

import org.example.model.User;
import org.example.repository.UserDAO;

import java.util.*;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Yeni kullanıcı ekleme
    public void addUser(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("İsim boş olamaz!");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email boş olamaz!");
        }

        User user = new User(name, email);
        userDAO.save(user);
    }

    // Tüm kullanıcıları listeleme
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    // ID’ye göre kullanıcı bulma
    public User getUserById(int id) {
        return userDAO.findById(id);
    }

    // Kullanıcı güncelleme
    public void updateUser(int id, String newName, String newEmail) {
        User user = userDAO.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("Kullanıcı bulunamadı (ID=" + id + ")");
        }
        if (newName != null && !newName.isBlank()) {
            user.setName(newName);
        }
        if (newEmail != null && !newEmail.isBlank()) {
            user.setEmail(newEmail);
        }
        userDAO.update(user);
    }

    // Kullanıcı silme
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    // Duplicate kayıtları bulup silme (email bazlı)
    public void removeDuplicateUsers() {
        List<User> allUsers = userDAO.findAll();
        Set<String> uniqueEmails = new HashSet<>();
        List<User> duplicatesToDelete = new ArrayList<>();

        for (User user : allUsers) {
            if (!uniqueEmails.add(user.getEmail())) {
                // Email zaten varsa bu kullanıcı duplicate'tir
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
    }
}
