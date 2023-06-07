package org.example.shapes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.Shape;

public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle() {
    }

    public Rectangle(double length,double width) {
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
    @JsonIgnore
    public double getArea() {
        return length * width;
    }

    @Override
    @JsonIgnore
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
