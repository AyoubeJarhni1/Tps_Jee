package com.tp;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try{
            MyRemoteServiceImpl myRemoteService= new MyRemoteServiceImpl();
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.rebind("MyRemoteService", myRemoteService);
            System.out.println("Server ready");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}