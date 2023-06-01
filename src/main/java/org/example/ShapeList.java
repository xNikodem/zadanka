package org.example;

import org.example.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeList {
    private List<Shape> shapes;

    public ShapeList() {
        this.shapes = new ArrayList<>();
    }

    public ShapeList(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
