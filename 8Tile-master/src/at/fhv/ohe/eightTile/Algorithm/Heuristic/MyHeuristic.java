package at.fhv.ohe.eightTile.Algorithm.Heuristic;

import at.fhv.ohe.eightTile.Field.Field;

import java.util.Comparator;
import java.util.List;

public class MyHeuristic implements IHeuristic {
    @Override
    public <T extends Comparable<T>> List<Field<T>> sortAfterHeuristic(List<Field<T>> fields) {
        fields.sort(Comparator.comparingInt(this::getValueFrom));
        return fields;
    }

    @Override
    public <T extends Comparable<T>> int getValueFrom(Field<T> node) {
        int neighbors = node.validTilesToMoves().size();
        int distance = node.manhattanDistance();
        return distance * neighbors;
    }
}
