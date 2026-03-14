package com.ensa.exerc4;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String name;
    private int quantity;
    private List<StockObserver> observers = new ArrayList<>();

    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (StockObserver observer : observers) {
            observer.update(this);  
        }
    }

    
    public void setQuantity(int newQuantity) {
        System.out.println("\nStock de '" + name + "' passe de " + this.quantity + " à " + newQuantity);
        this.quantity = newQuantity;
        notifyObservers();  
    }
}
