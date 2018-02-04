package at.fhv.ohe.graph.base;


import at.fhv.ohe.graph.exception.GraphParseException;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static at.fhv.ohe.graph.interpreter.GraphConstructionInterpreter.*;

/**
 * A final class for creating graphs. It uses a defined construct string that be parsed and insert into
 * a AdjacencyStructures that be inserted into the {@code Graph} class.
 */
public final class GraphFactory {
    private final static Pattern CONSTRUCT_VPATTERN = Pattern.compile("(V=\\{(([0-9])+,)*([0-9])+})");
    private final static Pattern CONSTRUCT_EPATTERN = Pattern.compile("(E=\\{([\\[<](([0-9])+,)*([0-9])+[]>],)*([\\[<](([0-9])+,)*([0-9])+[]>])})");

    /**
     * Keep the constructor private because we dont need an Object
     */
    private GraphFactory() {
    }

    //TODO Add Uri as Import

    /**
     * Creates an new Graph out of the given constructString and the given adjacency structure.
     * This factory type uses as vertex and edge type integer values.
     *
     * @param constructString {@code String} - The constructString that be used to create the graph.
     * @param type            {@code AdjacencyStructures} - An Enum type for set with {@code AdjacencyStructure} should be used
     * @return {@code Graph} - The created graph with the given parameters
     * @throws GraphParseException - Thrown if the given {@code constructString} is not readable
     */
    public static Graph<Integer, Integer> createGraph(String constructString, AdjacencyStructures type) throws GraphParseException {
        if (constructString == null) {
            throw new GraphParseException("The constructString can not be empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type cant be null");
        }

        Matcher verticesMatch = CONSTRUCT_VPATTERN.matcher(constructString);
        Matcher edgeMatch = CONSTRUCT_EPATTERN.matcher(constructString);

        if (!verticesMatch.find()) {
            throw new GraphParseException("No Vertex description found");
        }
        if (!edgeMatch.find()) {
            throw new GraphParseException("No Edge description found");
        }

        Set<Integer> vertices = parseVerticesString(verticesMatch.group());
        Set<EdgeTuple> edgeTuple = parseEdgeString(edgeMatch.group());

        AdjacencyStructure<Integer, Integer> instance = type.getInstance();
        instance.addVertices(vertices);
        for (EdgeTuple tuple : edgeTuple) {
            instance.addEdge(tuple.from, tuple.to, tuple.weight);
        }
        return new Graph<>(instance);
    }

    public static <V, E> Graph<V, E> createGraph(Set<V> vertices, AdjacencyStructures type) {
        if (vertices == null) throw new IllegalArgumentException("Set cant be null");
        if (type == null) throw new IllegalArgumentException("Type cant be null");

        AdjacencyStructure<V, E> instance = type.getInstance();
        instance.addVertices(vertices);
        return new Graph<>(instance);
    }


}