package at.fhv.ohe.eightTile.Field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void create_ok() {
        Integer start[][] = {{null,1,2},{3,4,5},{6,7,8}};

        Field<Integer> field = null;
        try {
             field = new Field<>(start);
        } catch (Exception e) {
            fail("Fail");
        }
        assertTrue(field.isWon());
    }

    @Test
    void create_null() {
        String exceptionMessage = "Array is not allowed to be null";

        Field<Integer> field = null;
        try {
            field = new Field<>(null);
            fail("No Exception is thrown");
        }catch (FieldCreateException e) {
            assertEquals(exceptionMessage, e.getMessage());
        } catch (Exception e) {
            fail("Wrong Exception is thrown");
        }
    }

    @Test
    void create_toLittle() {
        Integer start[][] = {{null,1},{2,3},{4,5}};
        String exceptionMessage = "Array size must be at minimum 3x3";

        Field<Integer> field = null;
        try {
            field = new Field<>(start);
            fail("No Exception is thrown");
        }catch (FieldCreateException e) {
            assertEquals(exceptionMessage, e.getMessage());
        } catch (Exception e) {
            fail("Wrong Exception is thrown");
        }
    }

    @Test
    void create_notEnoughField() {
        Integer start[][] = {{null,null,2},{3,4,5},{6,7,8}};
        String exceptionMessage = "No enough Fields found";

        Field<Integer> field = null;
        try {
            field = new Field<>(start);
            fail("No Exception is thrown");
        }catch (FieldCreateException e) {
            assertEquals(exceptionMessage, e.getMessage());
        } catch (Exception e) {
            fail("Wrong Exception is thrown");
        }
    }

    @Test
    void create_notUniqueField() {
        Integer start[][] = {{null,1,1},{3,4,5},{6,7,8}};
        String exceptionMessage = "Fields are not unique";

        Field<Integer> field = null;
        try {
            field = new Field<>(start);
            fail("No Exception is thrown");
        }catch (FieldCreateException e) {
            assertEquals(exceptionMessage, e.getMessage());
        } catch (Exception e) {
            fail("Wrong Exception is thrown");
        }
    }

    @Test
    void getWidth() {
        Integer _3X3[][] = {{null,1,2},{3,4,5},{6,7,8}};
        Integer _4X4[][] = {{null,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}};

        Field<Integer> field = null;

        field = new Field<>(_3X3);
        assertEquals(3, field.getWidth());

        field = new Field<>(_4X4);
        assertEquals(4, field.getWidth());
    }

    @Test
    void getHeight() {
        Integer _3X3[][] = {{null,1,2},{3,4,5},{6,7,8}};
        Integer _4X4[][] = {{null,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}};

        Field<Integer> field = null;

        field = new Field<>(_3X3);
        assertEquals(3, field.getHeight());

        field = new Field<>(_4X4);
        assertEquals(4, field.getHeight());
    }

    @Test
    void tilesToMove_2() {
        Integer start[][] = {{null,1,2},{3,4,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertEquals(2, field.validTilesToMoves().size());
    }

    @Test
    void tilesToMove_3() {
        Integer start[][] = {{1,null,2},{3,4,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertEquals(3, field.validTilesToMoves().size());
    }

    @Test
    void tilesToMove_4() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertEquals(4, field.validTilesToMoves().size());
    }

    @Test
    void doAllValidMoves_2() {
        Integer start[][] = {{null,1,2},{3,4,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertEquals(2, field.doAllValidMoves().size());
    }

    @Test
    void doAllValidMoves_3() {
        Integer start[][] = {{1,null,2},{3,4,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertEquals(3, field.doAllValidMoves().size());
    }

    @Test
    void doAllValidMoves_4() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertEquals(4, field.doAllValidMoves().size());
    }

    @Test
    void move_ok() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertTrue(field.isWon());

        moveTest(field,2);
        moveTest(field,5);
        moveTest(field,7);
        moveTest(field,4);
    }

    private void moveTest(Field<Integer> f, Integer tile) {
        assertTrue(f.move(4),"Move Fail At" + tile);
        assertFalse(f.isWon(), "State Fail At" + tile);
        assertTrue(f.move(4),"Move Fail At" + tile);
    }

    @Test
    void move_null() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        try {
            assertFalse(field.move(null));
        } catch (Exception ignore) {
            fail("An Exception is thrown");
        }
    }

    @Test
    void move_TileNotInField() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};
        String exceptionMessage = "Tile is not in GameField";
        Integer notInFieldTile = 42;

        Field<Integer> field = new Field<>(start);

        try {
            assertFalse(field.move(notInFieldTile));
            fail("An Exception is not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(exceptionMessage, e.getMessage());
        } catch (Exception ignore) {
           fail("Wrong Exception is thrown");
        }
    }

    @Test
    void move_NodeNotMovable() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        try {
            assertFalse(field.move(null));
        } catch (Exception ignore) {
            fail("An Exception is thrown");
        }
    }

    @Test
    void isWon_win() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        Field<Integer> field = new Field<>(start);

        assertTrue(field.isWon());
        field.move(2);
        assertFalse(field.isWon());
        field.move(2);
        assertTrue(field.isWon());
    }

    @Test
    void equalTest() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        Field<Integer> field0 = new Field<>(start);
        Field<Integer> field1 = new Field<>(start);

        assertTrue(field0.equals(field1));
    }

    @Test
    void hashCodeTest() {
        Integer start[][] = {{1,2,3},{4,null,5},{6,7,8}};

        int hashCode_0 = new Field<>(start).hashCode();
        int hashCode_1 = new Field<>(start).hashCode();

        assertTrue(hashCode_0 == hashCode_1);
    }

}