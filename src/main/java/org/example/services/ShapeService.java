package org.example.services;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import org.example.Shape;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShapeService {
    private final ObjectMapper mapper;

    public ShapeService() {
        this.mapper = new ObjectMapper();
    }


    public Shape findShapeWithLargestArea(List<Shape> shapes) {
        return shapes.stream().max(Comparator.comparing(Shape::getArea)).orElse(null);
    }

    public Shape findShapeWithLargestPerimeter(List<Shape> shapes, Class<? extends Shape> type) {
        return shapes.stream()
                .filter(type::isInstance)
                .max(Comparator.comparing(Shape::getPerimeter))
                .orElse(null);
    }

    public void exportShapesToJson(List<Shape> shapes, String path) {
        try {
            CollectionLikeType type= mapper.getTypeFactory().constructCollectionType(List.class,Shape.class);
            mapper.writerFor(type).writeValue(new File(path),shapes);
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    public List<Shape> importShapesFromJson(String path) {
        try {
            CollectionLikeType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Shape.class);
            return mapper.readValue(new File(path), type);
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
