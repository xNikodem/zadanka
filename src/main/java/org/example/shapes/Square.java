package org.example.shapes;

import org.example.Shape;

public class Square extends Shape {
    private double side;

    public Square() {
    }

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + Square.class.getSimpleName()+
                ", side=" + side +
                '}';
    }
}
