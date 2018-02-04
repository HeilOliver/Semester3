package at.fhv.ohe.graph.algorithm;

import at.fhv.ohe.graph.base.AdjacencyStructures;
import at.fhv.ohe.graph.base.Graph;
import at.fhv.ohe.graph.base.GraphFactory;
import at.fhv.ohe.graph.exception.GraphParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphCoherentCheckTest {


    @Test
    void coherentCheck_OnePart() {
        Graph<Integer,Integer> graph = null;
        String consString = "V={1,2,3} " +
                "E={<1,2,10>,<2,3,30>,<3,1,50>}";
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }

        GraphCoherentCheck<Integer, Integer> check = new GraphCoherentCheck<>();
        graph.runAlgorithm(check);

        assertEquals(1,check.getParts());
        assertEquals(GraphCoherentCheck.GraphCoherent.COHERENT,check.getType());
    }

    @Test
    void coherentCheck_NoRun() {
        GraphCoherentCheck<Integer, Integer> check = new GraphCoherentCheck<>();

        try {
            check.getType();
            fail("0");
        } catch (IllegalStateException ignore) {
        }
        try {
            check.getParts();
            fail("1");
        } catch (IllegalStateException ignore) {
        }
    }

    @Test
    void coherentCheck_ManyParts() {
        Graph<Integer,Integer> graph = null;
        String consString = "V={1,2,3,4} " +
                "E={<1,2,10>,<2,3,30>,<3,1,50>}";
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }

        GraphCoherentCheck<Integer, Integer> check = new GraphCoherentCheck<>();
        graph.runAlgorithm(check);

        assertEquals(2,check.getParts());
        assertEquals(GraphCoherentCheck.GraphCoherent.INCOHERENTLY,check.getType());
    }

    @Test
    void coherentCheck_NullChecks() {
        GraphCoherentCheck<Integer, Integer> check = new GraphCoherentCheck<>();

        try {
            check.doAlgorithm(null);
            fail("0");
        } catch (IllegalStateException ignore) {
        }

    }

}