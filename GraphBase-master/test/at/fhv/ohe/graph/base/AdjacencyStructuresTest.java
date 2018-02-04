package at.fhv.ohe.graph.base;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static at.fhv.ohe.graph.base.AdjacencyStructures.LIST;
import static at.fhv.ohe.graph.base.AdjacencyStructures.MATRIX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AdjacencyStructuresTest {

    @Test
    void crateInstance() {
        AdjacencyStructure listStructure;
        listStructure = LIST.getInstance();
        assertNotNull(listStructure);

        listStructure = MATRIX.getInstance();
        assertNotNull(listStructure);
    }

    @Test
    void crateGenericInstance() {
        AdjacencyStructure<String, Integer> listStructure;
        listStructure = LIST.getInstance();
        assertNotNull(listStructure);

        listStructure = MATRIX.getInstance();
        assertNotNull(listStructure);
    }

    @Test
    void crateDifferentInstance() {
        Set<String> values0 = Set.of("ABC", "AAA", "BBB", "CCC");
        Set<String> values1 = Set.of("DDD", "EEE", "FFF", "GGG");

        AdjacencyStructure<String, Integer> structure0;
        structure0 = MATRIX.getInstance();
        structure0.addVertices(values0);

        assertEquals(values0, structure0.getVertices());

        AdjacencyStructure<String, Integer> structure1;
        structure1 = MATRIX.getInstance();
        structure1.addVertices(values1);

        assertEquals(values1, structure1.getVertices());
        assertEquals(values0, structure0.getVertices());
    }
}