package org.example.shapes;

import org.junit.Assert;
import org.junit.Test;

public class CircleTest {

    @Test
    public void testCircleArea() {
        Circle circle = new Circle(5);
        Assert.assertEquals(78.54, circle.getArea(), 0.01);
    }

    @Test
    public void testCirclePerimeter() {
        Circle circle = new Circle(5);
        Assert.assertEquals(31.42, circle.getPerimeter(), 0.01);
    }

}

