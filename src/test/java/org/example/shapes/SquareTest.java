package org.example.shapes;

import org.junit.Assert;
import org.junit.Test;

public class SquareTest {

    @Test
    public void testGetArea() {
        // Given
        Square square = new Square(5);

        // When
        double area = square.getArea();

        // Then
        Assert.assertEquals(25.0, area, 0.001);
    }

    @Test
    public void testGetPerimeter() {
        // Given
        Square square = new Square(5);

        // When
        double perimeter = square.getPerimeter();

        // Then
        Assert.assertEquals(20.0, perimeter, 0.001);
    }
}

