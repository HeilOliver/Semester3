package at.fhv.ohe.eightTile.Algorithm.Heuristic;

import at.fhv.ohe.eightTile.Field.Field;

import java.util.Comparator;
import java.util.List;

public class MisplacedTiles implements IHeuristic {
    @Override
    public <T extends Comparable<T>> List<Field<T>> sortAfterHeuristic(List<Field<T>> fields) {
        fields.sort((Comparator.comparingInt(Field::numberMisplacedTiles)));
        return fields;
    }

    @Override
    public <T extends Comparable<T>> int getValueFrom(Field<T> node) {
        return node.numberMisplacedTiles();
    }
}
