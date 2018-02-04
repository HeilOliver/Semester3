package at.fhv.ohe.graph.base;

import at.fhv.ohe.graph.exception.GraphParseException;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GraphFactoryTest {

    @Test
    void testConstructionViaString_Fine() {
        String consString = "V={1,2,3} " +
                "E={[1,2,10],[2,1,10],[2,3,30],[3,1,50]}";

        Graph<Integer, Integer> graph;
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
            return;
        }

        graph.runAlgorithm(structure -> {
            Collection<Integer> vs = structure.getVertices();
            assertEquals(3, vs.size());
            assertTrue(vs.contains(1));
            assertTrue(vs.contains(2));
            assertTrue(vs.contains(3));

            Collection edges = structure.getEdges();
            assertEquals(4, edges.size());
            assertTrue(edges.contains(10));
            assertTrue(edges.contains(30));
            assertTrue(edges.contains(50));
        });
    }

    @Test
    void testConstructionViaString_SameVertices() {
        String consString = "V={1,2,3,1} " +
                "E={<2,1,10>,[2,3,30],[3,1,50]}";

        Graph<Integer, Integer> graph;
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }
    }

    @Test
    void testConstructionViaString_TwoVDef() {
        String consString = "V={1,2,3} " +
                "E={<2,1,10>,[2,3,30],[3,1,50]}" +
                "V={8,9,10} " +
                "V={7,6,5} ";

        Graph<Integer, Integer> graph;
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }
    }

    @Test
    void testConstructionViaString_Unidirectional() {
        String consString = "V={1,2,3} " +
                "E={<2,1,10>,[2,3,30],[3,1,50]}";

        Graph<Integer, Integer> graph;
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
            return;
        }

        graph.runAlgorithm(structure -> {
            Collection<Integer> vs = structure.getVertices();
            assertEquals(3, vs.size());
            assertTrue(vs.contains(1));
            assertTrue(vs.contains(2));
            assertTrue(vs.contains(3));

            Collection edges = structure.getEdges();
            assertEquals(4, edges.size());
            assertTrue(edges.contains(10));
            assertTrue(edges.contains(30));
            assertTrue(edges.contains(50));
        });
    }

    @Test
    void testConstructionViaString_NoVDescription() {
        String consString = "E={<2,1,10>,[2,3,30],[3,1,50]}";
        try {
            GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
            fail("");
        } catch (GraphParseException ignored) {
        }
    }

    @Test
    void testConstructionViaString_NoEDescription() {
        String consString = "V={1,2,3}";
        try {
            GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
            fail("");
        } catch (GraphParseException ignored) {
        }
    }

    @Test
    void testConstructionViaString_StringNull() {
        try {
            GraphFactory.createGraph((String) null, AdjacencyStructures.MATRIX);
            fail("");
        } catch (GraphParseException ignored) {
        }
    }

    @Test
    void testConstructionViaSet_SetNull() {
        try {
            GraphFactory.createGraph((Set<Object>) null, AdjacencyStructures.MATRIX);
            fail("");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void testConstructionViaSet_TypeNull() {
        try {
            GraphFactory.createGraph(Set.of(1, 2, 3), null);
            fail("");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void testConstructionViaString_TypeNull() {
        try {
            GraphFactory.createGraph("1,2,3", null);
            fail("");
        } catch (GraphParseException | IllegalArgumentException ignored) {
        }
    }

    @Test
    void testConstructionViaSet_Fine() {
        Set<Integer> integers = Set.of(1, 2, 3);
        Graph<Integer, String> graph;
        try {
            graph = GraphFactory.createGraph(integers, AdjacencyStructures.MATRIX);
            graph.runAlgorithm(structure -> {
                Collection<Integer> vs = structure.getVertices();
                assertEquals(3, vs.size());
                assertTrue(vs.contains(1));
                assertTrue(vs.contains(2));
                assertTrue(vs.contains(3));

                Collection edges = structure.getEdges();
                assertEquals(0, edges.size());
            });
        } catch (IllegalArgumentException ignored) {
            fail("");
        }


    }
}