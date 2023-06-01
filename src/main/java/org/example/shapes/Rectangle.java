package org.example.shapes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.example.Shape;

@JsonTypeName("rectangle")
public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle() {
    }

    @JsonCreator
    public Rectangle(@JsonProperty("length") double length, @JsonProperty("width") double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + Rectangle.class.getSimpleName()+
                ", length=" + length +
                ", width=" + width +
                '}';
    }
}
