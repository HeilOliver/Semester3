package at.fhv.ohe.multiplicandus.princess;

import at.fhv.ohe.graph.base.AdjacencyStructure;
import at.fhv.ohe.multiplicandus.ForestSpirit;
import at.fhv.ohe.multiplicandus.Princess;

import java.util.Set;

public class Klotmilde extends Princess {
    @Override
    public String returnBestPath() {
        Set<Integer> integers = paths.keySet();

        Integer minJewels = 999999999;
        for (Integer integer : integers) {
            if (integer < minJewels)
                minJewels = integer;
        }

        return paths.get(minJewels).toString() + "  Value: " + minJewels;
    }
}
