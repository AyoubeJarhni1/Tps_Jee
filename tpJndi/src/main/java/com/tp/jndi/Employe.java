package com.tp.jndi;


import java.io.Serializable;

public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;
    private String matricule;
    private String nom;
    private String poste;

    public Employe() {}

    public Employe(String matricule, String nom, String poste) {
        this.matricule = matricule;
        this.nom = nom;
        this.poste = poste;
    }
    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;}
    public String getPoste() {
        return poste;}
    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Override
    public String toString() {
        return "Employé{" +
                "matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", poste='" + poste + '\'' +
                '}';
    }
}