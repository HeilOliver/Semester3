package at.fhv.ohe.slider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SliderTest {


    @Test
    void create_WithArray() {
        // Array 10,10 = size 10,10
        Integer[][] arr = new Integer[10][10];
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = count++;
            }
        }
        arr[3][2] = null;

        Slider<Integer> slider = new Slider<>(arr);
        assertEquals(10, slider.getWidth());
        assertEquals(10, slider.getHeight());
    }

    @Test
    void create_WithArray_Null() {
        try {
            Slider slider = new Slider<>(null);
            fail("no exception Thrown");
        } catch (SliderCreateException e) {
            assertEquals("Array is not allowed to be null",e.getMessage());
        }
    }

    @Test
    void create_WithArray_emptyArray() {
        Integer[][] arr = new Integer[2][2];
        try {
            Slider slider = new Slider<>(arr);
            fail("no exception Thrown");
        } catch (SliderCreateException e) {
            assertEquals("No enough Fields found",e.getMessage());
        }
    }

    @Test
    void create_WithArray_multipleEmptySpots() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1; // - 1
        arr[1][0] = 2; // 2 -
        try {
            Slider slider = new Slider<>(arr);
            fail("no exception Thrown");
        } catch (SliderCreateException e) {
            assertEquals("No enough Fields found",e.getMessage());
        }
    }

    @Test
    void create_WithArray_equalField() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1; // - 1
        arr[1][0] = 2; // 2 2
        arr[1][1] = 2;
        try {
            Slider slider = new Slider<>(arr);
            fail("no exception Thrown");
        } catch (SliderCreateException e) {
            assertEquals("Fields are not unique",e.getMessage());
        }
    }


    @Test
    void move_CoordinatesOutOfBound() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1; // - 1
        arr[1][0] = 2; // 2 3
        arr[1][1] = 3;
        Slider<Integer> slider = new Slider<>(arr);
        try {
            slider.move(-1,0);
            fail("no exception Thrown");
        } catch (SliderMoveException e) {
            assertEquals("Coordinates are outside of Field", e.getMessage());
        }
        try {
            slider.move(-1,-1);
            fail("no exception Thrown");
        } catch (SliderMoveException e) {
            assertEquals("Coordinates are outside of Field", e.getMessage());
        }
        try {
            slider.move(0,-1);
            fail("no exception Thrown");
        } catch (SliderMoveException e) {
            assertEquals("Coordinates are outside of Field", e.getMessage());
        }
        try {
            slider.move(2,2);
            fail("no exception Thrown");
        } catch (SliderMoveException e) {
            assertEquals("Coordinates are outside of Field", e.getMessage());
        }
        try {
            slider.move(2,0);
            fail("no exception Thrown");
        } catch (SliderMoveException e) {
            assertEquals("Coordinates are outside of Field", e.getMessage());
        }
        try {
            slider.move(0,2);
            fail("no exception Thrown");
        } catch (SliderMoveException e) {
            assertEquals("Coordinates are outside of Field", e.getMessage());
        }
    }

    @Test
    void move_possibleField() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1; // - 1
        arr[1][0] = 2; // 2 3
        arr[1][1] = 3;
        Slider<Integer> slider = new Slider<>(arr);

        Integer[][] field = slider.getField();

        slider.move(1,0);
        slider.move(0,0);
        slider.move(0,1);
        slider.move(0,0);

        Integer[][] newField = slider.getField();
        assertEquals(field,newField);
    }

    @Test
    void move_notPossibleField() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1; // - 1
        arr[1][0] = 2; // 2 3
        arr[1][1] = 3;
        Slider<Integer> slider = new Slider<Integer>(arr);

        Integer[][] field = slider.getField();

        slider.move(0,0);
        slider.move(1,1);

        Integer[][] newField = slider.getField();
        assertEquals(field,newField);
    }

    @Test
    void stat_moveCounter() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1; // - 1
        arr[1][0] = 2; // 2 3
        arr[1][1] = 3;
        Slider<Integer> slider = new Slider<>(arr);

        assertEquals(0,slider.moveCounter());
        slider.move(0,1); // Gültiger Zug
        assertEquals(1,slider.moveCounter());
        slider.move(0,1); // Ungültiger Zug
        assertEquals(1,slider.moveCounter());
        slider.move(0,0); // Gültiger Zug
        assertEquals(2,slider.moveCounter());
    }

    @Test
    void stat_win() {
        Integer[][] arr = new Integer[2][2];
        arr[0][0] = 1;  // 1 -
        arr[1][0] = 2;  // 2 3
        arr[1][1] = 3;
        Slider<Integer> slider = new Slider<>(arr);

        assertFalse(slider.isWon());
        slider.move(0,0);
        assertTrue(slider.isWon());
    }

    @Test
    void stat_notWin() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1;  // - 1
        arr[1][0] = 2;  // 2 3
        arr[1][1] = 3;
        Slider<Integer> slider = new Slider<>(arr);

        assertTrue(slider.isWon());
        slider.move(0,1);
        assertFalse(slider.isWon());
    }

     @Test
    void stat_width() {
        int[] sizeX = {10,33,24,2,100};
        int[] sizeXExpected = {10,33,24,2,100};
        int[] sizeXResult= new int[sizeX.length];

         for (int i = 0; i < sizeX.length; i++) {
             Slider<Integer> slider = SliderFactory.createSliderBySize(sizeX[i], 4);
             sizeXResult[i] = slider.getWidth();
         }

         for (int i = 0; i < sizeXExpected.length; i++) {
             assertEquals(sizeXExpected[i], sizeXResult[i]);
         }
     }

    @Test
    void stat_height() {
        int[] sizeY = {10,33,24,2,100};
        int[] sizeYExpected = {10,33,24,2,100};
        int[] sizeYResult= new int[sizeY.length];

        for (int i = 0; i < sizeY.length; i++) {
            Slider<Integer> slider = SliderFactory.createSliderBySize(4, sizeY[i]);
            sizeYResult[i] = slider.getHeight();
        }

        for (int i = 0; i < sizeYExpected.length; i++) {
            assertEquals(sizeYExpected[i], sizeYResult[i]);
        }
    }

    @Test
    void sss() {
        Integer[][] arr = new Integer[2][2];
        arr[0][1] = 1;  // - 1
        arr[1][0] = 4;  // 2 3
        arr[1][1] = 3;
        Slider<Integer> slider = new Slider<>(arr);
        slider.isWon();
    }
}