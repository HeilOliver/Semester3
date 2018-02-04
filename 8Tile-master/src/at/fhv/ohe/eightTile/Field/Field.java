package at.fhv.ohe.eightTile.Field;

import java.util.*;

public class Field<T extends Comparable<T>> {
    private T[][] fieldState;
    private T[][] winningState;

    private Field(T[][] fieldState, T[][] winningState) {
        this.fieldState = fieldState;
        this.winningState = winningState;
    }

    Field(T[][] fieldState) {
        if (fieldState == null) throw new FieldCreateException("Array is not allowed to be null");
        if (fieldState.length < 3 || fieldState[0].length < 3)
            throw new FieldCreateException("Array size must be at minimum 3x3");

        ArrayList<T> values = new ArrayList<>();
        for (T[] fieldLine : fieldState) {
            for (T field : fieldLine) {
                if (field == null) continue;
                values.add(field);
            }
        }

        // Size
        if (values.size() != (fieldState.length * fieldState[0].length) - 1)
            throw new FieldCreateException("No enough Fields found");

        // Check if all Fields can be compared and nobody is equal
        Collections.sort(values);
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i).compareTo(values.get(i + 1)) >= 0)
                throw new FieldCreateException("Fields are not unique");
        }

        this.fieldState = fieldState;
        this.winningState = cloneField();
    }

    int getWidth() {
        return fieldState.length;
    }

    int getHeight() {
        return fieldState[0].length;
    }

    private T[][] cloneField() {
        T[][] clone = (T[][]) new Comparable[fieldState.length][fieldState[0].length];
        for (int i = 0; i < fieldState.length; i++) {
            clone[i] = fieldState[i].clone();
        }
        return clone;
    }

    public List<Field<T>> doAllValidMoves() {
        List<T> toMove = validTilesToMoves();
        List<Field<T>> newFields = new ArrayList<>(toMove.size());

        for (T tile : toMove) {
            Field<T> newField = new Field<>(cloneField(), winningState);
            newField.move(tile);
            newFields.add(newField);
        }

        return newFields;
    }

    public List<T> validTilesToMoves() {
        int x = -1;
        int y = -1;

        for (int i = 0; i < fieldState.length; i++) {
            for (int j = 0; j < fieldState[i].length; j++) {
                if (fieldState[i][j] != null) continue;
                x = i;
                y = j;
                break;
            }
            if (x != -1) break;
        }

        List<T> validTiles = new ArrayList<>();

        for (Directions directions : Directions.values()) {
            int calcX = directions.xModifier.calc(x);
            int calcY = directions.yModifier.calc(y);

            if (calcX < 0 || calcX >= getWidth()) continue;
            if (calcY < 0 || calcY >= getHeight()) continue;
            if (fieldState[calcX][calcY] == null) continue;
            validTiles.add(fieldState[calcX][calcY]);
        }
        return validTiles;
    }

    boolean move(T node) {
        int x = -1;
        int y = -1;

        if (node == null) {
            return false;
        }

        for (int i = 0; i < fieldState.length; i++) {
            for (int j = 0; j < fieldState[i].length; j++) {
                if (fieldState[i][j] == null) continue;
                if (!fieldState[i][j].equals(node)) continue;
                x = i;
                y = j;
                break;
            }
            if (x != -1) break;
        }

        if (x == -1 || y == -1) throw new IllegalArgumentException("Tile is not in GameField");

        for (Directions directions : Directions.values()) {
            int calcX = directions.xModifier.calc(x);
            int calcY = directions.yModifier.calc(y);

            if (calcX < 0 || calcX >= getWidth()) continue;
            if (calcY < 0 || calcY >= getHeight()) continue;
            if (fieldState[calcX][calcY] != null) continue;

            fieldState[calcX][calcY] = fieldState[x][y];
            fieldState[x][y] = null;
            return true;
        }
        return false;
    }

    public boolean isWon() {
        for (int x = 0; x < fieldState.length; x++) {
            for (int y = 0; y < fieldState[x].length; y++) {
                if (fieldState[x][y] == winningState[x][y]) continue;
                if (fieldState[x][y] == null || winningState[x][y] == null) return false;
                if (fieldState[x][y].equals(winningState[x][y])) continue;
                return false;
            }
        }
        return true;
    }

    private int manhattanDistanceNode(int isX, int isY) {
        T node = fieldState[isX][isY];
        int shouldX = -1;
        int shouldY = -1;
        int sum = 0;

        for (int x = 0; x < winningState.length; x++) {
            for (int y = 0; y < winningState[x].length; y++) {
                if (!node.equals(winningState[x][y])) continue;
                shouldX = x;
                shouldY = y;
            }
            if (shouldX != -1) break;
        }

        sum += Math.abs(shouldX - isX);
        sum += Math.abs(shouldY - isY);
        return sum;
    }

    public int numberMisplacedTiles() {
        LinkedList<T> values = new LinkedList<>();
        for (T[] fieldLine : fieldState) {
            for (T field : fieldLine) {
                if (field == null) continue;
                values.add(field);
            }
        }
        Collections.sort(values);
        values.addFirst(null);


        int wrong = 0;
        for (T[] aField : fieldState) {
            for (T anAField : aField) {
                if (anAField == null) {
                    if (values.poll() != null) wrong++;
                    continue;
                }
                if (anAField.equals(values.poll())) continue;
                wrong++;
            }
        }
        return wrong;
    }

    public int manhattanDistance() {
        int sum = 0;

        for (int x = 0; x < fieldState.length; x++) {
            for (int y = 0; y < fieldState[x].length; y++) {
                if (fieldState[x][y] == null) continue;
                sum += manhattanDistanceNode(x, y);
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n");
        for (T[] aField : fieldState) {
            s.append("[ ");
            for (T anAField : aField) {
                s.append(anAField == null ? "-" : anAField.toString());
                s.append(" ");
            }
            s.append("]\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field<?> field1 = (Field<?>) o;
        T[][] foreignField = (T[][]) field1.fieldState;

        if (fieldState.length != foreignField.length) return false;
        if (fieldState[0].length != foreignField[0].length) return false;

        for (int x = 0; x < fieldState.length; x++) {
            for (int y = 0; y < fieldState[0].length; y++) {
                if (fieldState[x][y] == null && foreignField[x][y] == null) continue;
                if (fieldState[x][y] == null || foreignField[x][y] == null) return false;
                if (!fieldState[x][y].equals(foreignField[x][y])) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        for (T[] aField : fieldState) {
            hash += Arrays.hashCode(aField);
        }

        return hash;
    }

    protected enum Directions {
        NORTH((x) -> x, (y) -> y - 1),
        EAST((x) -> x + 1, (y) -> y),
        SOUTH((x) -> x, (y) -> y + 1),
        WEST((x) -> x - 1, (y) -> y);

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
}
