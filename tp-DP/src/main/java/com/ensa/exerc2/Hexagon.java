package com.ensa.exerc2;

public class Hexagon implements Shape {
    private double side;

    public Hexagon(double side) {
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Hexagone avec côté = " + side);
    }
}
