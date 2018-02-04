package at.fhv.ohe.graph.algorithm;

import at.fhv.ohe.graph.base.AdjacencyStructure;
import at.fhv.ohe.graph.base.IGraphAlgorithm;

import java.util.Collection;

import static at.fhv.ohe.graph.algorithm.GraphCoherentCheck.GraphCoherent.*;

/**
 * An Algorithm for searching for an possible EulerWay. This Algorithm do not calculate the
 * EulerWay.
 * @param <V> - The VerticesType
 * @param <E> - The EdgesType
 */
public class GraphHashEulerWay<V,E> implements IGraphAlgorithm<V,E> {
    private EulerWayType result;
    private boolean calculated;

    public EulerWayType getResult() {
        if (!calculated) throw new IllegalStateException("The Algorithm has not been calculated");
        return result;
    }

    private EulerWayType calc(AdjacencyStructure<V,E> structure) {
        GraphCoherentCheck<V,E> check = new GraphCoherentCheck<>();
        check.doAlgorithm(structure);

        if (check.getType() == INCOHERENTLY) return EulerWayType.NO_PATH;

        Collection<V> vertices = structure.getVertices();
        int count = 0;

        for (V vertex : vertices) {
            if (count >= 2) break;
            if (structure.outputGrade(vertex) % 2 != 0) {
                count++;
            }
        }

        if (count == 0) return EulerWayType.CLOSED;
        if (count == 2) return EulerWayType.OPEN;
        return EulerWayType.NO_PATH;
    }

    @Override
    public void doAlgorithm(AdjacencyStructure<V,E> structure) {
        result = calc(structure);
        calculated = true;
    }

    public enum EulerWayType {
        OPEN,
        CLOSED,
        NO_PATH
    }
}
