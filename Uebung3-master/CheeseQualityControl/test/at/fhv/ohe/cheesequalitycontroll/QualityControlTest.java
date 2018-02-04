package at.fhv.ohe.cheesequalitycontroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QualityControlTest {

    private boolean[][] shortArrToBoolArr(short[][] arr) {
        boolean[][] booleans = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                booleans[i][j] = (arr[i][j] == 8);
            }
        }
        return booleans;
    }

    private void testCheese(int holeCount, int maxHoleSize, short[][] arr) {
        Cheese cheese = new Cheese(shortArrToBoolArr(arr));
        QualityControl control = new QualityControl();

        control.checkCheese(cheese);

        assertEquals(holeCount, control.getHoleCount());
        assertEquals(maxHoleSize, control.getMaxHoleSize());
    }

    @Test
    void checkCheese_oneHole() {
        short[][] onHole_Big = new short[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,8,8,0,0,0,0,0,0},
                {0,0,0,8,8,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};
        short[][] onHole_Small = new short[][]{
                {0,0,0,0,0,0,0},
                {0,0,8,0,0,0,0},
                {0,0,0,0,0,0,0}};

        short[][] oneHoles_tiny = new short[][]{
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,8,0,0,0,0},
                {0,0,0,0,0,0,0}};

        testCheese(1,4,onHole_Big);
        testCheese(1,1,onHole_Small);
        testCheese(1,1,oneHoles_tiny);
    }

    @Test
    void checkCheese_noHole() {
        short[][] noHole = new short[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};

        testCheese(0,0,noHole);
    }

    @Test
    void checkCheese_TwoHoles() {
        short[][] twoHoles_big = new short[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,8,8,0,0,0,0,0,0,0},
                {0,0,8,8,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,8,8,0,0},
                {0,0,0,0,0,0,0,8,8,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};

        short[][] twoHoles_small = new short[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,8,8,0,0,8,8,0,0},
                {0,0,0,8,8,0,0,8,8,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};

        short[][] twoHole_little = new short[][]{
                {0,0,0,0,0,0,0},
                {0,0,8,0,0,0,0},
                {0,0,0,0,8,0,0}};

        testCheese(2,4,twoHoles_big);
        testCheese(2,4,twoHoles_small);
        testCheese(2,1,twoHole_little);
    }

    @Test
    void checkCheese_MultiHole() {
        short[][] multi_Hole = new short[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,8,8,0,0,0,8,0,0},
                {0,0,8,0,0,8,0,0,0,0,0},
                {0,0,8,0,0,8,0,0,0,0,0},
                {0,0,0,8,8,0,0,0,8,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,8,8,0,0,8,8,0,0},
                {0,0,0,8,0,8,0,8,8,0,0},
                {0,0,0,8,8,0,0,0,0,0,0}};
        testCheese(5,8,multi_Hole);
    }

    @Test
    void checkCheese_OneBig() {
        short[][] oneBig = new short[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,8,8,8,0,0,0,0},
                {0,0,0,8,0,0,0,8,0,0,0},
                {0,0,0,8,0,0,0,8,0,0,0},
                {0,0,0,8,0,0,0,8,0,0,0},
                {0,0,0,8,8,8,8,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}};

        testCheese(1,13,oneBig);
    }

    @Test
    void checkCheese_HoleAtBound() {
        short[][] oneHole_bound = new short[][]{
                {8,8,8,8,8,8,8},
                {8,0,0,0,0,0,8},
                {8,8,8,8,8,8,8}};

        testCheese(1,16,oneHole_bound);
    }
}