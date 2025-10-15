package com.example;

import java.io.Serializable;

public class Contact implements Serializable {
    private String nom;
    private String email;
    private String telephone;

    public Contact(String nom, String email, String telephone) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
    }
    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String toString() {
        return "Nom: " + nom + ", Email: " + email + ", Téléphone: " + telephone;
    }
}
