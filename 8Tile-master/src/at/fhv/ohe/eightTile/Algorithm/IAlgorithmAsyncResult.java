package at.fhv.ohe.eightTile.Algorithm;

import at.fhv.ohe.eightTile.Field.Field;

import java.util.List;

public interface IAlgorithmAsyncResult {

    <T extends Comparable<T>> void asyncResult(List<Field<T>> solution);

}
