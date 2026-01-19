package com.ensa.connection;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private static DatabaseConnection instanceDB = null ;
    private static long creationTimeMs;
    private List<String> users ;

    private DatabaseConnection() {
        long start = System.currentTimeMillis();
        System.out.println("Connexion simulée à la base de données créée !");
        users = new ArrayList<>();
        users.add("Ayoub");
        users.add("Anas");
        users.add("Mounir");
        long end = System.currentTimeMillis();
        creationTimeMs = end - start;
    }

    public static DatabaseConnection getInstance() {
        long start = System.currentTimeMillis();
        if (instanceDB == null) {
            instanceDB = new DatabaseConnection();
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Durée d'accés à l'instance:"+ duration + "ms");
        return instanceDB;
    }

    public List<String> getUsers() {
        return users;
    }

    public void addUser( String user) {
        users.add(user);
        System.out.println("user added successfully");
    }

    public static long getCreationTimeMs() {
        return creationTimeMs;
    }


}
