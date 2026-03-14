package com.ensa.exerc2;

import java.awt.*;

public class Triangle implements Shape {

    private double x;
    private double y;
    @Override
    public void draw() {
        System.out.println("Triangle draw");
    }

    public Triangle (double x, double y) {
        this.x = x;
        this.y = y;
    }
}
