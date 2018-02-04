package at.fhv.ohe.graph.base;

public enum AdjacencyStructures {

    LIST(AdjacencyList::new),
    MATRIX(AdjacencyMatrix::new);

    IAdjacencySupplier instantiation;

    AdjacencyStructures(IAdjacencySupplier instantiation) {
        this.instantiation = instantiation;
    }

    /**
     * Return an new generic instance of the backed AdjacencyStructure
     *
     * @return {@code AdjacencyStructure} - a new instance of the backed AdjacencyStructure
     */
    <V, E> AdjacencyStructure<V, E> getInstance() {
        return instantiation.get();
    }

    private interface IAdjacencySupplier {
        <V, E> AdjacencyStructure<V, E> get();
    }
}
