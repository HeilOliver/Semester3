package at.fhv.ohe.graph.base;

import java.util.Collection;
import java.util.Set;

public abstract class AdjacencyStructure<V, E> {

    abstract String print();

    public abstract void addVertices(Collection<V> vertices);

    public void addVertices(V vertices) {
        addVertices(Set.of(vertices));
    }

    public abstract E addEdge(V from, V to, E weight);

    public void addEdge(V from, V to, E weight, boolean undirected) {
        if (undirected) addEdge(to, from, weight);
        addEdge(from, to, weight);
    }

    public abstract E removeEdge(V from, V to);

    public abstract int entryGrade(V vertice);

    public abstract int outputGrade(V vertice);

    @SuppressWarnings("SimplifiableIfStatement")
    public boolean isUndirected(V v1, V v2) {
        E e1 = getEdge(v1, v2);
        E e2 = getEdge(v2, v1);

        if (e1 == null && e2 == null)
            return true;

        if (e1 == null || e2 == null)
            return false;

        return e1.equals(e2);
    }

    public abstract boolean isConnected(V from, V to);

    public abstract E getEdge(V from, V to);

    public abstract Collection<E> getEdges();

    public abstract Collection<V> getVertices();

    public abstract Collection<V> getPossibleVertices(V vertex);
}
