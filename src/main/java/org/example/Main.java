package org.example;

import org.example.services.ShapeService;
import org.example.shapes.Circle;
import org.example.shapes.Rectangle;
import org.example.shapes.Square;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ShapeService shapeService = new ShapeService();

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(4));
        shapes.add(new Rectangle(5, 3));
        shapes.add(new Circle(2));

        Shape largestAreaShape = shapeService.findShapeWithLargestArea(shapes);
        System.out.println("Shape with the largest area: " + largestAreaShape);

        Shape largestPerimeterRectangle = shapeService.findShapeWithLargestPerimeter(shapes, Rectangle.class);
        System.out.println("Rectangle with the largest perimeter: " + largestPerimeterRectangle);

        String path = "shapes.json";
        shapeService.exportShapesToJson(shapes, path);

        List<Shape> importedShapes = shapeService.importShapesFromJson(path);
        System.out.println("Imported shapes: " + importedShapes);
    }
}