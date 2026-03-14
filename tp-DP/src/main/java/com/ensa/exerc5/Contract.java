package com.ensa.exerc5;

public class Contract extends Document {
    private String client;
    private double amount;
    private boolean signed;

    public Contract(String title, String author, String content, String client, double amount) {
        super(title, author, content);
        this.client = client;
        this.amount = amount;
        this.signed = false;
    }

    @Override
    public void printInfo() {
        System.out.println("CONTRAT : " + title);
        System.out.println("  Client : " + client + " | Montant : " + amount + " DH");
        System.out.println("  Signé : " + (signed ? "Oui" : "Non"));
        System.out.println("  Auteur : " + author);
    }

    public void sign() {
        this.signed = true;
        System.out.println("Contrat signé !");
    }

    @Override
    public Document clone() {
        Contract clone = new Contract(this.title, this.author, this.content, this.client, this.amount);
        clone.signed = this.signed;
        return clone;
    }

    public String getClient() { return client; }
    public double getAmount() { return amount; }
    public boolean isSigned() { return signed; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setClient(String client) { this.client = client; }
}