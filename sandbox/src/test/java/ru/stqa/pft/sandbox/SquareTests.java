package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testSquare() {
        Square s = new Square(5.0);
        Assert.assertEquals(s.square(), 20.0);
    }
}
