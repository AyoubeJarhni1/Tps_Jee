package com.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LivreServiceImpl extends UnicastRemoteObject implements LivreService {

  private List<Livre> livres;

    protected LivreServiceImpl() throws RemoteException {
        super();
        livres = new ArrayList<>();
    }

    @Override
    public String ajouterLivre(Livre livre) {
        return "Livre ajouté: " + livre.getTitre() + " par " + livre.getAuteur();
    }

    @Override
    public Livre rechercherLivre(String titre) {

        return new Livre(titre, "Auteur Inconnu", 2024);
    }

    @Override
    public List<Livre> listerLivres() {
        return livres;
    }
    
  
}
