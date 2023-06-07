package org.example.shapes;

import org.example.Shape;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + Circle.class.getSimpleName()+
                ", radius=" +radius+
                '}';
    }
}
