package org.example.services;

import org.example.Shape;
import org.example.shapes.Rectangle;
import org.example.shapes.Square;
import org.example.shapes.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShapeServiceTest {
    private ShapeService shapeService;

    @BeforeEach
    public void setUp() {
        shapeService = new ShapeService();
    }

    @Test
    public void testFindShapeWithLargestArea() {
        Shape rect = Mockito.mock(Rectangle.class);
        when(rect.getArea()).thenReturn(12.0);
        Shape square = Mockito.mock(Square.class);
        when(square.getArea()).thenReturn(16.0);
        Shape circle = Mockito.mock(Circle.class);
        when(circle.getArea()).thenReturn(28.27);

        List<Shape> shapes = Arrays.asList(rect, square, circle);

        Shape largestShape = shapeService.findShapeWithLargestArea(shapes);
        assertEquals(circle, largestShape);
    }

    @Test
    public void testFindShapeWithLargestPerimeter() {
        Shape rect = Mockito.mock(Rectangle.class);
        when(rect.getPerimeter()).thenReturn(14.0);
        Shape square = Mockito.mock(Square.class);
        when(square.getPerimeter()).thenReturn(16.0);
        Shape circle = Mockito.mock(Circle.class);
        when(circle.getPerimeter()).thenReturn(18.85);

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
    @Test
    public void testImportShapesFromJson_ThrowsException_WhenFileNotFound() {
        String wrongPath = "wrong_path.json";
        assertThrows(IOException.class, () -> shapeService.importShapesFromJson(wrongPath));
    }

    @Test
    public void testExportShapesToJson_ThrowsException_WhenFileUnreachable() {
        Rectangle rect = new Rectangle(3, 4);
        Square square = new Square(4);
        Circle circle = new Circle(3);
        List<Shape> shapes = Arrays.asList(rect, square, circle);
        String unreachablePath = "/unreachable_path/shapes.json";

        assertThrows(IOException.class, () -> shapeService.exportShapesToJson(shapes, unreachablePath));
    }


}
