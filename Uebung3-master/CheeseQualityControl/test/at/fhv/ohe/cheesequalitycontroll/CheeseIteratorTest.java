package at.fhv.ohe.cheesequalitycontroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheeseIteratorTest {
    private Cheese _cheese;

    @BeforeEach
    void setUp() {
        boolean[][] arr = new boolean[10][10];
        // mark corners
        arr[0][0] = true;
        arr[9][9] = true;
        arr[9][0] = true;
        arr[0][9] = true;

        // mark random Points
        arr[4][2] = true;
        arr[4][3] = true;
        arr[9][3] = true;

        _cheese = new Cheese(arr);
    }

    @Test
    void testIterator_AllPoints() {
        ICheeseIterator iterator = _cheese.getIterator();

        assertTrue(iterator.isInHole(0,0), "0,0");
        assertTrue(iterator.isInHole(9,9), "9,9");
        assertTrue(iterator.isInHole(9,0), "9,0");
        assertTrue(iterator.isInHole(0,9), "0,9");
        assertTrue(iterator.isInHole(4,2), "4,2");
        assertTrue(iterator.isInHole(4,3), "4,3");
        assertTrue(iterator.isInHole(9,3), "9,3");
    }

    @Test
    void testIterator_OnlyOneTimeTrue() {
        ICheeseIterator iterator = _cheese.getIterator();

        iterator.isInHole(0,0);
        iterator.isInHole(9,9);
        iterator.isInHole(9,0);
        iterator.isInHole(0,9);
        iterator.isInHole(4,2);
        iterator.isInHole(4,3);
        iterator.isInHole(9,3);

        assertFalse(iterator.isInHole(0,0), "0,0");
        assertFalse(iterator.isInHole(9,9), "9,9");
        assertFalse(iterator.isInHole(9,0), "9,0");
        assertFalse(iterator.isInHole(0,9), "0,9");
        assertFalse(iterator.isInHole(4,2), "4,2");
        assertFalse(iterator.isInHole(4,3), "4,3");
        assertFalse(iterator.isInHole(9,3), "9,3");
    }

    @Test
    void testIterator_OutOfBound() {
        ICheeseIterator iterator = _cheese.getIterator();

        assertFalse(iterator.isInHole(-1,-1), "-1,-1");
        assertFalse(iterator.isInHole(10,10), "10,10");
        assertFalse(iterator.isInHole(4,10), "4,10");
        assertFalse(iterator.isInHole(10,4), "10,4");
    }

    @Test
    void testIterator_DifferentIterator() {
        ICheeseIterator iterator = _cheese.getIterator();
        ICheeseIterator iterator2 = _cheese.getIterator();

        assertFalse(iterator.equals(iterator2), "Same Object?");

        assertTrue(iterator.isInHole(0,0), "0,0");
        assertFalse(iterator.isInHole(0,0), "0,0");

        assertTrue(iterator2.isInHole(0,0), "0,0");
        assertFalse(iterator2.isInHole(0,0), "0,0");
    }
}