package at.fhv.ohe.graph.base;

import at.fhv.ohe.graph.exception.GraphParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GraphTest {

    Graph graph;

    @BeforeEach
    void instantiateGraph() {
        String consString = "V={1,2,3} " +
                "E={<1,2,10>,[2,3,30],[3,1,50]}";
        try {
            this.graph = GraphFactory.createGraph(consString, AdjacencyStructures.MATRIX);
        } catch (GraphParseException e) {
            fail("Parse fail but string is correct");
        }
    }


    @Test
    void runAlgorithm_sync() {
        IGraphAlgorithmMock mock = new IGraphAlgorithmMock();

        assertEquals(0, mock.eCount);
        assertEquals(0, mock.vCount);
        graph.runAlgorithm(mock);
        assertEquals(4, mock.eCount);
        assertEquals(3, mock.vCount);
    }

    @Test
    void runAlgorithm_async() {
        IGraphAlgorithmMock mock = new IGraphAlgorithmMock();

        assertEquals(0, mock.eCount);
        assertEquals(0, mock.vCount);
        graph.runAlgorithm(mock, algorithm -> {
            assertEquals(4, mock.eCount);
            assertEquals(3, mock.vCount);
        });
    }
}