package at.fhv.ohe.eightTile.Algorithm;

import at.fhv.ohe.eightTile.Field.Field;

import java.util.List;

public interface IAlgorithm {

    <T extends Comparable<T>> List<Field<T>> solve(Field<T> startPoint);

    default <T extends Comparable<T>> void asyncSolve(Field<T> startPoint, IAlgorithmAsyncResult asyncResult) {
        new Thread(() -> {
            List<Field<T>> solve = solve(startPoint);
            asyncResult.asyncResult(solve);
        }).start();
    }
}
