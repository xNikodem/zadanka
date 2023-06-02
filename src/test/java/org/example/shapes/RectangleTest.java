package org.example.shapes;

import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void testRectangleArea() {
        Rectangle rectangle = new Rectangle(5, 4);
        Assert.assertEquals(20, rectangle.getArea(), 0.01);
    }

    @Test
    public void testRectanglePerimeter() {
        Rectangle rectangle = new Rectangle(5, 4);
        Assert.assertEquals(18, rectangle.getPerimeter(), 0.01);
    }
}

