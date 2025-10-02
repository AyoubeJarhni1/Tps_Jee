package com.tp;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MyRemoteService myRemoteService = (MyRemoteService) registry.lookup("MyRemoteService");
            String response = myRemoteService.sayHello();
            System.out.println("Response from Server: " + response);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
