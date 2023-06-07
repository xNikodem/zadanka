package org.example.services;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.Shape;
import org.example.serialization.ShapeDeserializer;
import org.example.serialization.ShapeSerializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShapeService {
    private final ObjectMapper mapper;

    public ShapeService() {
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();
        module.addSerializer(Shape.class, new ShapeSerializer());
        module.addDeserializer(Shape.class, new ShapeDeserializer());
        mapper.registerModule(module);
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
            mapper.writeValue(new File(path), shapes);
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    public List<Shape> importShapesFromJson(String path) {
        try {
            return mapper.readValue(new File(path), mapper.getTypeFactory().constructCollectionType(List.class, Shape.class));
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}