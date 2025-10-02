package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIServer_GestionLivres {
    public static void main(String[] args) {
         try{
            LivreService livreService = new LivreServiceImpl();
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.rebind ("MyLivreService", livreService);
            System.out.println("Server is ready.");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}