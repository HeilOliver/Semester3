package at.fhv.ohe.multiplicandus.princess;

import at.fhv.ohe.graph.base.AdjacencyStructure;
import at.fhv.ohe.multiplicandus.ForestSpirit;
import at.fhv.ohe.multiplicandus.Princess;

import java.util.Set;

public class Klotwilde extends Princess {
    @Override
    public String returnBestPath() {
        Set<Integer> integers = paths.keySet();

        Integer maxJewels = 0;
        for (Integer integer : integers) {
            if (integer > maxJewels)
                maxJewels = integer;
        }

        return paths.get(maxJewels).toString() + "  Value: " + maxJewels;
    }
}
