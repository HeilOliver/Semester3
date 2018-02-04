package at.fhv.ohe.cheesequalitycontroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheeseTest {

    @Test
    void getWidth() {
        boolean[][] cheeseArr = new boolean[10][5];
        int expected = 10;
        int result;

        Cheese cheese = new Cheese(cheeseArr);
        result = cheese.getWidth();
        assertEquals(result,expected);
    }

    @Test
    void getHeight() {
        boolean[][] cheeseArr = new boolean[10][5];
        int expected = 5;
        int result;

        Cheese cheese = new Cheese(cheeseArr);
        result = cheese.getHeight();
        assertEquals(result,expected);
    }

    @Test
    void getIterator() {
        boolean[][] cheeseArr = new boolean[10][5];

        ICheeseIterator result;
        Cheese cheese = new Cheese(cheeseArr);
        result = cheese.getIterator();
        assertTrue(result != null);
    }
}