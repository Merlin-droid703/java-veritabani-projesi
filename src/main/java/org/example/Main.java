package org.example;

import org.example.repository.UserTableManager;

public class Main {
    public static void main(String[] args) {
        UserTableManager tableManager = new UserTableManager();

        tableManager.recreateUserTable();
    }
}
