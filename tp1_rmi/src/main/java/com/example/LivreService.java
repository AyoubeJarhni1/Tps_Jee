package com.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LivreService extends Remote {
    String ajouterLivre(Livre livre) throws RemoteException;
    Livre rechercherLivre(String titre) throws RemoteException;
    List<Livre> listerLivres() throws RemoteException;
    
}
