package at.fhv.ohe.eightTile.Field;

import java.util.List;
import java.util.Random;


public class FieldFactory {

    public static Field<Node> createField() {
        Node[][] arr = {
                {null, new Node(1), new Node(2)},
                {new Node(3), new Node(4), new Node(5)},
                {new Node(6), new Node(7), new Node(8)}};
        return createField(arr);
    }

    public static <T extends Comparable<T>> Field<T> createField(T[][] endState) {
        Field<T> field = new Field<T>(endState);
        return shuffleField(field);
    }

    public static <T extends Comparable<T>> Field<T> createField(T[][] endState, long seed, int iterations) {
        Field<T> field = new Field<T>(endState);
        return shuffleField(field, seed, iterations);
    }

    public static <T extends Comparable<T>> Field<T> shuffleField(Field<T> field) {
        return shuffleField(field, System.nanoTime(), 150);
    }

    public static <T extends Comparable<T>> Field<T> shuffleField(Field<T> field, long seed, int iterations) {
        if (iterations < 0)
            throw new IllegalArgumentException("Iteration must be equal or greater than 0");

        Random r = new Random(seed);
        for (int i = 0; i < iterations; i++) {
            List<T> fields = field.validTilesToMoves();
            field.move(fields.get(r.nextInt(fields.size())));
        }

        return field;
    }
}
