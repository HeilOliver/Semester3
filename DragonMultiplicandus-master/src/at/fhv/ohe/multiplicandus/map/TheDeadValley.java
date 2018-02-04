package at.fhv.ohe.multiplicandus.map;


import at.fhv.ohe.graph.base.*;
import at.fhv.ohe.multiplicandus.ForestSpirit;
import at.fhv.ohe.multiplicandus.ForestSpirit.JewelOperator;
import at.fhv.ohe.multiplicandus.Princess;

import java.lang.invoke.MutableCallSite;
import java.util.List;
import java.util.Set;

import static at.fhv.ohe.multiplicandus.ForestSpirit.JewelOperator.*;

public class TheDeadValley implements IGraphAlgorithm<String, ForestSpirit>{
    private final Set<String> vertexes = Set.of("Castle","B","C","D","E","F","G","H","I","J","K","Dragon");

    @Override
    public void doAlgorithm(AdjacencyStructure<String, ForestSpirit> adjacencyStructure) {
        adjacencyStructure.addVertices(vertexes);
        adjacencyStructure.addEdge("Castle","B",new ForestSpirit(SUBTRACT,19),true);
        adjacencyStructure.addEdge("Castle","D",new ForestSpirit(MULTIPLICATION,5),true);
        adjacencyStructure.addEdge("Castle","F",new ForestSpirit(ADD,42),true);
        adjacencyStructure.addEdge("B","D",new ForestSpirit(ADD,121),true);
        adjacencyStructure.addEdge("B","C",new ForestSpirit(ADD,171),true);
        adjacencyStructure.addEdge("F","D",new ForestSpirit(DIVIDE,3),true);
        adjacencyStructure.addEdge("F","H",new ForestSpirit(SUBTRACT,-17),true);
        adjacencyStructure.addEdge("D","E",new ForestSpirit(MULTIPLICATION,4),true);
        adjacencyStructure.addEdge("E","C",new ForestSpirit(DIVIDE,5),true);
        adjacencyStructure.addEdge("E","I",new ForestSpirit(SUBTRACT,-21),true);
        adjacencyStructure.addEdge("H","I",new ForestSpirit(MULTIPLICATION,24),true);
        adjacencyStructure.addEdge("H","K",new ForestSpirit(ADD,285),true);
        adjacencyStructure.addEdge("I","K",new ForestSpirit(SUBTRACT,265),true);
        adjacencyStructure.addEdge("K","Dragon",new ForestSpirit(MULTIPLICATION,3),true);
        adjacencyStructure.addEdge("I","Dragon",new ForestSpirit(ADD,317),true);
        adjacencyStructure.addEdge("I","G",new ForestSpirit(DIVIDE,8),true);
        adjacencyStructure.addEdge("Dragon","G",new ForestSpirit(DIVIDE,3),true);
        adjacencyStructure.addEdge("Dragon","J",new ForestSpirit(SUBTRACT,9),true);
        adjacencyStructure.addEdge("G","J",new ForestSpirit(MULTIPLICATION,12),true);
        adjacencyStructure.addEdge("C","J",new ForestSpirit(MULTIPLICATION,3),true);
    }
}
