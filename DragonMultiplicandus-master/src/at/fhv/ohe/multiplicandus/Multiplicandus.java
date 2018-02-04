package at.fhv.ohe.multiplicandus;

import at.fhv.ohe.graph.base.*;

import java.util.Set;

public class Multiplicandus {
    private Graph<String, ForestSpirit> graph;

    public Multiplicandus(IGraphAlgorithm<String, ForestSpirit> map) {
        createGraph(map);
    }

    private void createGraph(IGraphAlgorithm<String, ForestSpirit> map) {
        graph = GraphFactory.createGraph(Set.of(), AdjacencyStructures.MATRIX);
        graph.runAlgorithm(map);
    }

    public String findTheWayOfGlory(Princess princess) {
        graph.runAlgorithm(princess);
        return princess.returnBestPath();
    }

}
