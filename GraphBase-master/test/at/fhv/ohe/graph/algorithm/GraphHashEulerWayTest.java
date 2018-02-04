package at.fhv.ohe.graph.algorithm;

import at.fhv.ohe.graph.base.AdjacencyStructures;
import at.fhv.ohe.graph.base.Graph;
import at.fhv.ohe.graph.base.GraphFactory;
import at.fhv.ohe.graph.exception.GraphParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphHashEulerWayTest {

    @Test
    void eulerWay_Open() {
        Graph<Integer,Integer> graph = null;
        String consString = "V={1,2,3} " +
                "E={<1,2,10>,<2,3,30>}";
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }

        GraphHashEulerWay<Integer, Integer> check = new GraphHashEulerWay<>();
        graph.runAlgorithm(check);

        assertEquals(GraphHashEulerWay.EulerWayType.OPEN, check.getResult());
    }

    @Test
    void eulerWay_Closed() {
        Graph<Integer,Integer> graph = null;
        String consString = "V={1,2,3} " +
                "E={<1,2,10>,<2,3,30>,<3,1,50>}";
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }

        GraphHashEulerWay<Integer, Integer> check = new GraphHashEulerWay<>();
        graph.runAlgorithm(check);

        assertEquals(GraphHashEulerWay.EulerWayType.CLOSED, check.getResult());
    }

    @Test
    void eulerWay_No() {
        Graph<Integer,Integer> graph = null;
        String consString = "V={1,2,3,4} " +
                "E={<1,2,10>,<2,3,30>,<3,1,50>}";
        try {
            graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }

        GraphHashEulerWay<Integer, Integer> check = new GraphHashEulerWay<>();
        graph.runAlgorithm(check);

        assertEquals(GraphHashEulerWay.EulerWayType.NO_PATH, check.getResult());
    }

    @Test
    void eulerWay_Null() {
        GraphHashEulerWay<Integer, Integer> check = new GraphHashEulerWay<>();
        try {
            check.doAlgorithm(null);
            fail("0");
        } catch (IllegalStateException ignore) {
        }

    }

    @Test
    void eulerWay_NotRun() {
        GraphHashEulerWay<Integer, Integer> check = new GraphHashEulerWay<>();
        try {
            check.getResult();
            fail("0");
        } catch (IllegalStateException ignore) {
        }

    }
}