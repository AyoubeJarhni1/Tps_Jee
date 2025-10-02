package com.example;

import java.io.Serializable;
import java.util.UUID;


public class Livre implements Serializable {

    private UUID id;
    private String titre;
    private String auteur;
    private int anneePublication;

    public Livre(String titre, String auteur, int anneePublication) {
        this.id = UUID.randomUUID();
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
    }

    public UUID getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }
    
}
