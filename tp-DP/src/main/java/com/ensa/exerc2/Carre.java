package com.ensa.exerc2;

import java.io.Serializable;

public class Carre implements Shape{

    private double side;

    public Carre(double side){
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Carre draw");
    }

}
