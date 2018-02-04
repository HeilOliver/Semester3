package at.fhv.ohe.graph.algorithm;

import at.fhv.ohe.graph.base.AdjacencyStructure;
import at.fhv.ohe.graph.base.IGraphAlgorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Check the Graph for his Coherence.
 * @param <V> - The VerticesType
 * @param <E> - The EdgesType
 */
public class GraphCoherentCheck<V,E>  implements IGraphAlgorithm<V,E> {
    private Set<V> closedList;
    private AdjacencyStructure<V,E> structure;
    private GraphCoherent type;
    private int parts;
    private boolean calculated;

    public GraphCoherentCheck() {
        this.closedList = new HashSet<>();
    }

    private void traverseNodes(V n) {
        if (closedList.contains(n)) return;

        closedList.add(n);
        for (V o : structure.getPossibleVertices(n)) {
            traverseNodes(o);
        }

    }

    @Override
    public void doAlgorithm(AdjacencyStructure<V,E> structure) {
        if (structure == null) {
            throw new IllegalStateException("Structure is not allowed to be null");
        }

        this.structure = structure;
        Collection<V> vertices = structure.getVertices();

        for (V vertex : vertices) {
            if (closedList.contains(vertex)) continue;

            parts++;
            traverseNodes(vertex);
            if (closedList.size() == vertices.size()) break;
        }

        if (parts <= 1) {
            type = GraphCoherent.COHERENT;
        } else {
            type = GraphCoherent.INCOHERENTLY;
        }
        calculated = true;
    }

    public GraphCoherent getType() {
        if (!calculated) throw new IllegalStateException("Algorithm is not calculated");
        return type;
    }

    public int getParts() {
        if (!calculated) throw new IllegalStateException("Algorithm is not calculated");
        return parts;
    }

    public enum GraphCoherent {
        INCOHERENTLY,
        COHERENT
    }
}
