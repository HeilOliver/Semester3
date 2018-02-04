package at.fhv.ohe.multiplicandus.princess;

import at.fhv.ohe.graph.base.AdjacencyStructure;
import at.fhv.ohe.multiplicandus.ForestSpirit;
import at.fhv.ohe.multiplicandus.Princess;

import java.util.*;

public class Klothilde extends Princess {
    @Override
    public String returnBestPath() {
        if (!paths.containsKey(999)) {
            return "- :( -";
        }
        return paths.get(999).toString() + "  Value: " + 999;
    }
}
