package at.fhv.ohe.graph.base;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static at.fhv.ohe.graph.base.AdjacencyStructures.LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjacencyStructureListTest {

    @Test
    void print() {
        AdjacencyStructure<String, Integer> structure = LIST.getInstance();
        structure.addVertices(Set.of("A", "B", "C"));
        structure.addEdge("A", "B", 100);
        structure.addEdge("B", "C", 10);
        structure.addEdge("A", "C", 1);

        String expected = "A->\t[B, 100]\n\t[C, 1]\nB->\t[C, 10]\nC->\n";
        String result = structure.print();
        assertEquals(expected, result);
    }
}