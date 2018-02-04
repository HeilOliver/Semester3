package at.fhv.ohe.slider;

import java.util.*;

public class Slider<T extends Comparable<T>> {
    private T[][] field;
    private int madeMoves;

    /**
     * Creates an Slider Field with the given arr.
     * @param arr - The initiated Array
     *
     * @throws SliderCreateException - If {@code arr} is null
     * @throws SliderCreateException - If {@code arr} size is 0
     * @throws SliderCreateException - If {@code arr} has no empty spot
     * @throws SliderCreateException - If {@code arr} a fields value is the same like a another one
     */
    public Slider(T[][] arr) {
        if (arr == null) throw new SliderCreateException("Array is not allowed to be null");
        if (arr.length < 2 || arr[0].length < 2)
            throw new SliderCreateException("Array size must be greater than 1 ");

        ArrayList<T> values = new ArrayList<>();
        for (T[] fieldLine : arr) {
            for (T field : fieldLine) {
                if (field == null) continue;
                values.add(field);
            }
        }

        // Size
        if (values.size() != (arr.length*arr[0].length) -1)
            throw new SliderCreateException("No enough Fields found");

        // Check if all Fields can be compared and nobody is equal
        Collections.sort(values);
        for (int i = 0; i < values.size() -1; i++) {
            if (values.get(i).compareTo(values.get(i +1)) >= 0)
                throw new SliderCreateException("Fields are not unique");
        }

        field = arr;
    }

    /**
     * Returns the Width of the game Field
     * @return - the width of the game field.
     */
    public int getWidth() {
        return field.length;
    }

    /**
     * Returns the Height of the game Field
     * @return - the height of the game Field
     */
    public int getHeight() {
        return field[0].length;
    }

    /**
     * Moves the specified Field to the empty position.
     * @param x - The fields x position
     * @param y - The fields y position
     * @throws SliderMoveException - if {@code x} or {@code y} is out of bound
     */
    public void move(int x, int y) {
        if (x > getWidth()-1 || x < 0) throw new SliderMoveException("Coordinates are outside of Field");
        if (y > getHeight()-1 || y < 0) throw new SliderMoveException("Coordinates are outside of Field");

        for (Directions directions : Directions.values()) {
            int calcX = directions.xModifier.calc(x);
            int calcY = directions.yModifier.calc(y);

            if (calcX < 0 || calcX >= getWidth()) continue;
            if (calcY < 0 || calcY >= getHeight()) continue;
            if (field[calcX][calcY] != null) continue;

            field[calcX][calcY] = field[x][y];
            field[x][y] = null;
            madeMoves++;
            break;
        }
    }

    protected enum Directions {
        NORTH((x)-> x, (y)-> y - 1),
        EAST((x)-> x + 1, (y)-> y),
        SOUTH((x)-> x, (y)-> y + 1),
        WEST((x)-> x - 1, (y)-> y);

        protected final IModifyCoordinate xModifier;
        protected final IModifyCoordinate yModifier;

        Directions(IModifyCoordinate xModifier, IModifyCoordinate yModifier) {
            this.xModifier = xModifier;
            this.yModifier = yModifier;
        }

        protected interface IModifyCoordinate {
            int calc(int value);
        }
    }

    /**
     * Returns the gameFields underlying Array.
     * @return - an 2Dimensional Array with all the positions
     */
    public T[][] getField() {
        return field;
    }

    /**
     * Returns how many moves already been done.
     * It counts only the possible moves.
     * @return - The count of the moves that already been made
     */
    public int moveCounter() {
        return madeMoves;
    }

    /**
     * Checks if the Field is in its End position.
     * @return Return {@code True} if the Field is in its End position
     * Returns {@code False} if the Field is not in the End position
     */
    public boolean isWon() {
        if (field[0][1] == null) return false;

        for (int i = 2; i < getWidth()*getHeight(); i++) {
            T oldField = field[(i -1) / getHeight()][(i -1) % getWidth()];
            T nextField = field[i / getHeight()][i % getWidth()];
            if (nextField == null) return false;

            if (oldField.compareTo(nextField) >= 0) return false;
        }
        return true;
    }
}
