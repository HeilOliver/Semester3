package at.fhv.ohe.eightTile.Algorithm.Heuristic;

import at.fhv.ohe.eightTile.Field.Field;

import java.util.List;

public interface IHeuristic {

    <T extends Comparable<T>> List<Field<T>> sortAfterHeuristic(List<Field<T>> fieldList);

    <T extends Comparable<T>> int getValueFrom(Field<T> node);
}
