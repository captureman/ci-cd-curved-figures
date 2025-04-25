package org.example;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {
    @Test
    public void testPaintRed() {
        ShapeFactory factory = new ShapeFactory(18);
        assertEquals(Color.red, factory.paint);
    }
}
