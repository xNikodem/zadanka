package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Shape;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ShapeService {
    public Shape findShapeWithLargestArea(List<Shape> shapes) {
        return shapes.stream().max(Comparator.comparing(Shape::getArea)).orElse(null);
    }

    public <T extends Shape> T findShapeWithLargestPerimeter(List<Shape> shapes, Class<T> type) {
        return shapes.stream()
                .filter(type::isInstance)
                .max(Comparator.comparing(Shape::getPerimeter))
                .map(type::cast)
                .orElse(null);
    }

    public void exportShapesToJson(List<Shape> shapes, String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), shapes);
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    public List<Shape> importShapesFromJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(new File(path), Shape[].class));
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}