package org.example.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.example.Shape;

@JsonTypeName("square")
public class Square extends Shape {
    private double side;

    public Square() {
    }

    @JsonCreator
    public Square(@JsonProperty("side") double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    @JsonIgnore
    public double getArea() {
        return side * side;
    }

    @Override
    @JsonIgnore
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
