package at.fhv.ohe.graph.base;

public interface IGraphAlgorithm<V, E> {

    void doAlgorithm(AdjacencyStructure<V, E> structure);
}
