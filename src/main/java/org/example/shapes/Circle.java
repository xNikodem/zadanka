package org.example.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.example.Shape;

@JsonTypeName("circle")
public class Circle extends Shape {
    private double radius;

    public Circle() {
    }

    @JsonCreator
    public Circle(@JsonProperty("radius") double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + Circle.class.getSimpleName()+
                ", radius=" + radius +
                '}';
    }
}
