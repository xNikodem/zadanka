package org.example.shapes;

import org.junit.Assert;
import org.junit.Test;

public class SquareTest {

    @Test
    public void testSquareArea() {
        Square square = new Square(5);
        Assert.assertEquals(25, square.getArea(), 0.01);
    }

    @Test
    public void testSquarePerimeter() {
        Square square = new Square(5);
        Assert.assertEquals(20, square.getPerimeter(), 0.01);
    }
}


