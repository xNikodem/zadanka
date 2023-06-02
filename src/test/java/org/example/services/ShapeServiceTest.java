package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.example.Shape;
import org.example.shapes.Rectangle;
import org.example.shapes.Square;
import org.example.shapes.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapeServiceTest {
    private ShapeService shapeService;
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.activateDefaultTypingAsProperty(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, "@type");
        shapeService = new ShapeService(mapper);
    }

    @Test
    public void testFindShapeWithLargestArea() {
        Rectangle rect = new Rectangle(3, 4); // area = 12
        Square square = new Square(4); // area = 16
        Circle circle = new Circle(3); // area = 28.27
        List<Shape> shapes = Arrays.asList(rect, square, circle);

        Shape largestShape = shapeService.findShapeWithLargestArea(shapes);
        assertEquals(circle, largestShape);
    }

    @Test
    public void testFindShapeWithLargestPerimeter() {
        Rectangle rect = new Rectangle(3, 4); // perimeter = 14
        Square square = new Square(4); // perimeter = 16
        Circle circle = new Circle(3); // perimeter = 18.85
        List<Shape> shapes = Arrays.asList(rect, square, circle);

        Shape largestShape = shapeService.findShapeWithLargestPerimeter(shapes, Circle.class);
        assertEquals(circle, largestShape);
    }

    @Test
    public void testExportShapesToJson() throws IOException {
        Rectangle rect = new Rectangle(3, 4);
        Square square = new Square(4);
        Circle circle = new Circle(3);
        List<Shape> shapes = Arrays.asList(rect, square, circle);
        String path = "shapes.json";

        shapeService.exportShapesToJson(shapes, path);
        File file = new File(path);
        assertTrue(file.exists() && !file.isDirectory());
    }

    @Test
    public void testImportShapesFromJson() throws IOException {
        String path = "shapes.json";
        List<Shape> shapes = shapeService.importShapesFromJson(path);
        assertFalse(shapes.isEmpty());
    }
}
