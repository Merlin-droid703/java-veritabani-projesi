package org.example;

import org.example.repository.UserRepository;
import org.example.repository.UserTableManager;

public class Main {
    public static void main(String[] args) {

        UserTableManager tableManager = new UserTableManager();
        tableManager.recreateUserTable();

        System.out.println("--- Kullanıcı Ekleme İşlemi Başlatılıyor ---");

        UserRepository userRepository = new UserRepository();

        userRepository.addUser("Ali", "ali@mail.com");

        userRepository.addUser("Veli", "veli@mail.com");
    }
}