package com.ensa;

import com.ensa.exerc1.connection.DatabaseConnection;
import com.ensa.exerc4.DailyReportObserver;
import com.ensa.exerc4.EmailAlertObserver;
import com.ensa.exerc4.Stock;
import com.ensa.exerc4.UIObserver;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // DatabaseConnection instance = DatabaseConnection.getInstance();
        // DatabaseConnection instance2 = DatabaseConnection.getInstance();
        // List<String> users = instance.getUsers();
        // users = new ArrayList<>();
        // users.add("Ayoub");
        // users.add("Anas");
        // users.add("Mounir");
        // System.out.println("Même instance ? " + (instance2 == instance));
        // System.out.println(instance==instance2);
        // instance2.addUser("Mouad");
        // System.out.println("users from instance "+instance.getUsers());
        // instance2.addUser("Mohammed");
        // System.out.println("users from instance 2"+instance2.getUsers());

        Stock laptop = new Stock("Laptop Dell", 15);

        EmailAlertObserver emailAlert = new EmailAlertObserver();
        UIObserver ui = new UIObserver();
        DailyReportObserver report = new DailyReportObserver();

        laptop.addObserver(emailAlert);
        laptop.addObserver(ui);
        laptop.addObserver(report);

        laptop.setQuantity(2);  
        laptop.setQuantity(8);    
        laptop.setQuantity(5);    
        laptop.setQuantity(20);   

        DailyReportObserver.printDailyReport();
    }

    
}