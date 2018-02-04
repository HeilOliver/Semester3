package at.fhv.ohe.graph.base;


public class Graph<V, E> {
    private AdjacencyStructure<V, E> structure;

    Graph(AdjacencyStructure<V, E> structure) {
        this.structure = structure;
    }

    /**
     * This method calls the {@code print} method from
     * the underlying {@code AdjacencyStructure} and return the result.
     *
     * @return - {@code String} returns a formatted string with the
     * format of the underlying {@code AdjacencyStructure}
     */
    public String print() {
        return structure.print();
    }

    /**
     * This method run the given algorithm with it self.
     *
     * @param algorithm - The given algorithm that should be run.
     */
    public void runAlgorithm(IGraphAlgorithm<V, E> algorithm) {
        if (algorithm == null) throw new IllegalArgumentException("The algorithm cant be null");
        algorithm.doAlgorithm(structure);
    }

    /**
     * Start an algorithm asynchronous. If the algorithm is finished the
     * result method in {@code IGraphAsynchronousResult} will be raised.
     *
     * @param algorithm    {@code IGraphAlgorithm} - The algorithm that should be run
     * @param asynchronous {@code IGraphAsynchronousResult} - The callback for the result.
     */
    public void runAlgorithm(IGraphAlgorithm<V, E> algorithm, IGraphAsynchronousResult asynchronous) {
        if (asynchronous == null) throw new IllegalArgumentException("The asynchronous callback cant be null");
        if (algorithm == null) throw new IllegalArgumentException("The algorithm cant be null");

        new Thread(() -> {
            runAlgorithm(algorithm);
            asynchronous.graphResult(algorithm);
        }).start();
    }

}
