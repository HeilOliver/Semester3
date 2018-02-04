package at.fhv.ohe.multiplicandus;

import at.fhv.ohe.graph.base.AdjacencyStructure;
import at.fhv.ohe.graph.base.IGraphAlgorithm;

import java.util.*;

public abstract class Princess implements IGraphAlgorithm<String, ForestSpirit> {
    private Stack<ForestSpirit> knownSpirits = new Stack<>();
    private Stack<String> knownWayPoints = new Stack<>();
    private AdjacencyStructure<String, ForestSpirit> map;

    private int doWork(int jewels) {
        if (knownSpirits.size() != 0)
            jewels = knownSpirits.peek().doMagicThing(jewels);
        return jewels;
    }

    protected Map<Integer,List<String>> paths = new HashMap<>();

    private void findWay(String node, int jewels) {
        knownWayPoints.push(node);
        if ((jewels = doWork(jewels)) < 0) {
            knownWayPoints.pop();
            knownSpirits.pop();
            return;
        }

        if (node.equals("Dragon")) {
            ArrayList<String> wayPoints = new ArrayList<>(knownWayPoints);
            paths.put(jewels, wayPoints);
        }

        for (String s : map.getPossibleVertices(node)) {
            ForestSpirit edge = map.getEdge(node, s);
            if (knownSpirits.contains(edge)) continue;

            knownSpirits.push(edge);
            findWay(s,jewels);
        }
        knownWayPoints.pop();
        if (knownSpirits.size() != 0)
            knownSpirits.pop();
    }

    public abstract String returnBestPath();

    @Override
    public void doAlgorithm(AdjacencyStructure<String, ForestSpirit> adjacencyStructure) {
        map = adjacencyStructure;
        findWay("Castle",33);
    }
}
