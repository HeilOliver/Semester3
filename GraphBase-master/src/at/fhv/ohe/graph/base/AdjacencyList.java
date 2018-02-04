package at.fhv.ohe.graph.base;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AdjacencyList<V, E> extends AdjacencyStructure<V, E> {
    private Map<V, List<AdjacencyListEntry>> resolver;

    public AdjacencyList() {
        resolver = new HashMap<>();
    }

    @Override
    String print() {
        Set<V> set = resolver.keySet();
        StringBuilder sb = new StringBuilder();

        for (V vertex : set) {
            sb.append(vertex.toString());
            sb.append("->");
            List<AdjacencyListEntry> list = resolver.get(vertex);
            if (list.isEmpty()) {
                sb.append("\n");
                continue;
            }

            for (AdjacencyListEntry entry : list) {
                sb.append("\t");
                sb.append(entry.toString());
                sb.append("\n");
            }
        }


        return sb.toString();
    }

    @Override
    public void addVertices(Collection<V> vertices) {
        if (vertices == null) return;

        for (V vertex : vertices) {
            resolver.put(vertex, new LinkedList<>());
        }
    }

    @Override
    public E addEdge(V from, V to, E weight) { // TODO neuer Text
        if (from == null) throw new IllegalArgumentException("From is null");
        if (to == null) throw new IllegalArgumentException("From is null");
        if (!resolver.containsKey(from)) throw new IllegalArgumentException("From is not Found");
        if (!resolver.containsKey(to)) throw new IllegalArgumentException("To is not Found");

        List<AdjacencyListEntry> entries = resolver.get(from);
        if (weight == null) return removeEdge(from, to);

        for (AdjacencyListEntry entry : entries) {
            if (entry.to.equals(to)) {
                E temp = entry.weight;
                entry.weight = weight;
                return temp;
            }
        }
        AdjacencyListEntry entry = new AdjacencyListEntry(to, weight);
        entries.add(entry);

        return weight;
    }

    @Override
    public E removeEdge(V from, V to) { // TODO neuer Text
        if (from == null) throw new IllegalArgumentException("From is null");
        if (to == null) throw new IllegalArgumentException("From is null");
        if (!resolver.containsKey(from)) throw new IllegalArgumentException("From is not Found");
        if (!resolver.containsKey(to)) throw new IllegalArgumentException("To is not Found");

        List<AdjacencyListEntry> entries = resolver.get(from);
        AdjacencyListEntry tempEntry = null;

        for (AdjacencyListEntry entry : entries) {
            if (entry.to.equals(to)) {
                tempEntry = entry;
                break;
            }
        }
        entries.remove(tempEntry);
        return (tempEntry == null) ? null : tempEntry.weight;
    }

    @Override
    public int entryGrade(V vertice) {
        if (vertice == null) throw new IllegalArgumentException("Vertice is null");
        if (!resolver.containsKey(vertice)) throw new IllegalArgumentException("Vertice is not Found");

        AtomicInteger count = new AtomicInteger();

        resolver.forEach((k, v) -> v.forEach((e) -> {
            if (e.to.equals(vertice)) count.getAndIncrement();
        }));

        return count.intValue();
    }

    @Override
    public int outputGrade(V vertice) {
        if (vertice == null) throw new IllegalArgumentException("Vertice is null");
        if (!resolver.containsKey(vertice)) throw new IllegalArgumentException("Vertice is not Found");

        return resolver.get(vertice).size();
    }

    @Override
    public boolean isConnected(V from, V to) { // TODO neuer Text
        if (from == null) throw new IllegalArgumentException("From is null");
        if (to == null) throw new IllegalArgumentException("From is null");
        if (!resolver.containsKey(from)) throw new IllegalArgumentException("From is not Found");
        if (!resolver.containsKey(to)) throw new IllegalArgumentException("To is not Found");

        List<AdjacencyListEntry> entries = resolver.get(from);
        for (AdjacencyListEntry entry : entries) {
            if (entry.to.equals(to)) return true;
        }
        return false;
    }

    @Override
    public E getEdge(V from, V to) { // TODO neuer Text
        if (from == null) throw new IllegalArgumentException("From is null");
        if (to == null) throw new IllegalArgumentException("From is null");
        if (!resolver.containsKey(from)) throw new IllegalArgumentException("From is not Found");
        if (!resolver.containsKey(to)) throw new IllegalArgumentException("To is not Found");

        List<AdjacencyListEntry> entries = resolver.get(from);
        for (AdjacencyListEntry entry : entries) {
            if (entry.to.equals(to)) return entry.weight;
        }
        return null;
    }

    @Override
    public Collection<E> getEdges() {
        ArrayList<E> list = new ArrayList<>();
        resolver.forEach((k, v) -> v.forEach((e) -> {
            list.add(e.weight);
        }));
        return list;
    }

    @Override
    public Collection<V> getVertices() {
        Set<V> set = resolver.keySet();
        return Collections.unmodifiableSet(set);
    }

    @Override
    public Collection<V> getPossibleVertices(V vertex) {
        if (vertex == null) throw new IllegalArgumentException("Vertex is null");
        if (!resolver.containsKey(vertex)) throw new IllegalArgumentException("Vertex is not Found");

        List<AdjacencyListEntry> entries = resolver.get(vertex);
        Set<V> possibleNeighbors = new HashSet<>();

        for (AdjacencyListEntry entry : entries) {
            possibleNeighbors.add(entry.to);
        }
        return possibleNeighbors;
    }

    private class AdjacencyListEntry {
        V to;
        E weight;

        private AdjacencyListEntry(V to, E weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "[" + to + ", " + weight + "]";
        }
    }
}
