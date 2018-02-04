package at.fhv.ohe.graph.base;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static at.fhv.ohe.graph.base.AdjacencyStructures.MATRIX;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjacencyStructureMatrixTest {

    @Test
    void print() {
        AdjacencyStructure<String, Integer> structure = MATRIX.getInstance();
        structure.addVertices(Set.of("A", "B", "C"));
        structure.addEdge("A", "B", 100);
        structure.addEdge("B", "C", 10);
        structure.addEdge("A", "C", 1);

        String expected = "\t A\t B\t C\nA\t -\t 100\t 1\nB\t -\t -\t 10\nC\t -\t -\t -";
        String result = structure.print();
        assertEquals(expected, result);
    }
}