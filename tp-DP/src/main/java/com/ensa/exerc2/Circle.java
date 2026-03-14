package com.ensa.exerc2;

public class Circle implements Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public void draw() {
        System.out.println(" Draw Circle");
    }
}
