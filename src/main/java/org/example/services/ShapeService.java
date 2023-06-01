package org.example.services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.example.Shape;
import org.example.ShapeList;

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
        ShapeList shapeList = new ShapeList(shapes);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.activateDefaultTypingAsProperty(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, "@type");
        try {
            mapper.writeValue(new File(path), shapeList);
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    public List<Shape> importShapesFromJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.activateDefaultTypingAsProperty(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, "@type");
        try {
            ShapeList shapeList = mapper.readValue(new File(path), ShapeList.class);
            return shapeList.getShapes();
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}