package com.ensa.exerc2;

public class ShapeFactory {


        public Shape createShape(String type) {
            switch (type.toLowerCase()) {
                case "square":
                    return new Carre(5);
                case "circle":
                    return new Circle(3);
                case "triangle":
                    return new Triangle(4, 6);
                case "hexagon":
                    return new Hexagon(4);
                default:
                    throw new IllegalArgumentException("Type de forme inconnu : " + type);
            }
        }

        public static void main(String[] args) {
            ShapeFactory factory = new ShapeFactory();
            String[] types = {"square", "circle", "triangle", "hexagon"};
            for (String type : types) {
            Shape shape = factory.createShape(type);
            System.out.println("Test draw pour : " + type);
            shape.draw();
            System.out.println();
            }
        }

}
