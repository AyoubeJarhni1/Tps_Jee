package com.ensa.tp8.models;

public class Product {
    private int id;
    private String nom;
    private String description;

    // Default constructor for JSON deserialization
    public Product() {
    }

    public Product(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
