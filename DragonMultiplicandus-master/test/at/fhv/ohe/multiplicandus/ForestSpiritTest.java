package at.fhv.ohe.multiplicandus;

import org.junit.jupiter.api.Test;

import static at.fhv.ohe.multiplicandus.ForestSpirit.JewelOperator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ForestSpiritTest {

    @Test
    void testOperator_Add() {

        int[][] values = {{10, 10}, {0, 10}, {10, 0}, {0, -10}, {-10, 0}, {-10, -10}, {0, 0}, {10, -10}, {-10, 10}};
        int[] expected = {20, 10, 10, -10, -10, -20, 0, 0, 0};

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], ADD.calc(values[i][0], values[i][1]), i + "");
        }
    }

    @Test
    void testOperator_Subtract() {

        int[][] values = {{10, 10}, {0, 10}, {10, 0}, {0, -10}, {-10, 0}, {-10, -10}, {0, 0}, {10, -10}, {-10, 10}};
        int[] expected = {0, -10, 10, 10, -10, 0, 0, 20, -20};

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], SUBTRACT.calc(values[i][0], values[i][1]), i + "");
        }
    }

    @Test
    void testOperator_Multiplication() {

        int[][] values = {{10, 10}, {0, 10}, {10, 0}, {0, -10}, {-10, 0}, {-10, -10}, {0, 0}, {10, -10}, {-10, 10}};
        int[] expected = {100, 0, 0, 0, 0, 100, 0, -100, -100};

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], MULTIPLICATION.calc(values[i][0], values[i][1]), i + "");
        }
    }

    @Test
    void testOperator_Division() {

        int[][] values = {{10, 10}, {0, 10}, {10, 0}, {0, -10}, {-10, 0}, {-10, -10}, {0, 0}, {10, -10}, {-10, 10}};
        int[] expected = {1, 0, -1, 0, -1, 1, -1, -1, -1};

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], DIVIDE.calc(values[i][0], values[i][1]), i + "");
        }
    }


}