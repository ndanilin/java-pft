package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;
import ru.stqa.pdf.sandbox.Point;

import static org.testng.Assert.assertEquals;

public class PointTest {

    @Test
    public void testDistanceOne() {
        Point point = new Point(1, 1);
        assertEquals(point.distance(new Point(3, 3)), 2.8284271247461903);
    }

    @Test
    public void testDistanceTwo() {
        Point point = new Point(-1, 8);
        assertEquals(point.distance(new Point(7, -2)), 12.806248474865697);
    }
}
