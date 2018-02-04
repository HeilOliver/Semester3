package at.fhv.ohe.graph.base;

import java.util.*;

public class AdjacencyMatrix<V, E> extends AdjacencyStructure<V, E> {
    private E[][] edges;
    private Map<V, Integer> resolver;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrix() {
        resolver = new HashMap<>();
        edges = (E[][]) new Object[0][0];
    }

    private int[] getIndex(V from, V to) {
        if (from == null) throw new IllegalArgumentException("From value is not to be allowed to be null");
        if (to == null) throw new IllegalArgumentException("To value is not to be allowed to be null");

        Integer fromIndex = resolver.get(from);
        Integer toIndex = resolver.get(to);

        if (fromIndex == null) throw new IllegalArgumentException("From Vertex is not in Structure");
        if (toIndex == null) throw new IllegalArgumentException("To Vertex is not in Structure");
        return new int[]{fromIndex, toIndex};
    }

    @Override
    public E removeEdge(V from, V to) {
        return addEdge(from, to, null);
    }

    @Override
    public int entryGrade(V vertice) {
        Integer verticeIndex = resolver.get(vertice);
        if (verticeIndex == null) {
            throw new IllegalArgumentException("Vertice is not in Structure");
        }

        int count = 0;
        for (E[] e : edges) {
            if (e[verticeIndex] == null) continue;
            count++;
        }
        return count;
    }

    @Override
    public int outputGrade(V vertice) {
        Integer verticeIndex = resolver.get(vertice);
        if (verticeIndex == null) {
            throw new IllegalArgumentException("Vertice is not in Structure");
        }

        int count = 0;
        for (E e : edges[verticeIndex]) {
            if (e == null) continue;
            count++;
        }
        return count;
    }


    @Override
    public boolean isConnected(V from, V to) {
        int[] index = getIndex(from, to);
        return edges[index[0]][index[1]] != null;
    }

    @Override
    public E getEdge(V from, V to) {
        int[] index = getIndex(from, to);
        return edges[index[0]][index[1]];
    }


    @Override
    public Collection<E> getEdges() {
        Collection<E> collection = new ArrayList<>();
        for (E[] edge : edges) {
            for (E e : edge) {
                if (e == null) continue;
                collection.add(e);
            }
        }
        return collection;
    }

    @Override
    public Collection<V> getVertices() {
        return Collections.unmodifiableSet(resolver.keySet());
    }

    @Override
    public Collection<V> getPossibleVertices(V vertex) {
        if (vertex == null) throw new IllegalArgumentException("Vertex value is not to be allowed to be null");
        Integer index = resolver.get(vertex);
        if (index == null) throw new IllegalArgumentException("Vertex is not in Structure");

        Set<V> neighbors = new HashSet<>();
        Collection<V> vertices = getVertices();
        for (V v : vertices) {
            if (getEdge(vertex,v) == null) continue;

            neighbors.add(v);
        }
        return neighbors;
    }


    @Override
    String print() {
        StringBuilder sb = new StringBuilder();
        ArrayList<V> list = new ArrayList<>(resolver.keySet());

        for (V v : list) {
            sb.append("\t ");
            sb.append(v.toString());
        }

        for (V v : list) {
            sb.append("\n");
            sb.append(v.toString());
            for (V v1 : list) {
                sb.append("\t ");
                E entry = edges[resolver.get(v)][resolver.get(v1)];
                sb.append(entry == null ? "-" : entry.toString());
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addVertices(Collection<V> vertices) {
        if (vertices == null) {
            return;
        }

        int i = resolver.size();
        for (V vertex : vertices) {
            resolver.put(vertex, i++);
        }

        E[][] newEdges = (E[][]) new Object[resolver.size()][resolver.size()];

        if (edges.length != 0) {
            for (int j = 0; j < edges.length; j++) {
                System.arraycopy(edges[j], 0, newEdges[j], 0, edges[0].length);
            }
        }
        edges = newEdges;
    }

    @Override
    public E addEdge(V from, V to, E weight) {
        int[] index = getIndex(from, to);

        E lastValue = edges[index[0]][index[1]];
        edges[index[0]][index[1]] = weight;

        return (lastValue == null) ? weight : lastValue;
    }

}
