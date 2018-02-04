package at.fhv.ohe.graph.base;

public class IGraphAlgorithmMock implements IGraphAlgorithm<Integer, Integer> {
    int vCount;
    int eCount;

    @Override
    public void doAlgorithm(AdjacencyStructure<Integer, Integer> structure) {
        vCount = structure.getVertices().size();
        eCount = structure.getEdges().size();
    }
}
