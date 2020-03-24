package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testPointItself() {
        Point a = new Point(1, 1);
        Point a1 = new Point(1, 1);
        Assert.assertEquals(a.distance(a1), 0);

    }

    @Test
    public void testPointZeroToOne() {
        Point zero = new Point(0, 0);
        Point one = new Point(1, 1);
        Assert.assertEquals(zero.distance(one), Math.sqrt(2));
    }

    @Test
    public void testPointOneLevelY() {
        Point zero = new Point(1, 1);
        Point one = new Point(3, 1);
        Assert.assertEquals(zero.distance(one), 2);
    }

    @Test
    public void testPointNiceRoot() {
        Point zero = new Point(3, 3);
        Point one = new Point(0, -1);
        Assert.assertEquals(zero.distance(one), 5);

    }
}

