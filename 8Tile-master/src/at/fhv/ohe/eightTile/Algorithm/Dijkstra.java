package at.fhv.ohe.eightTile.Algorithm;

import at.fhv.ohe.eightTile.Algorithm.Heuristic.IHeuristic;
import at.fhv.ohe.eightTile.Field.Field;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Dijkstra implements IAlgorithm {

    private IHeuristic heuristic;
    private int counter = 0;
    private int expanded = 0;

    public Dijkstra() {
    }

    public Dijkstra(IHeuristic heuristic) {
        this.heuristic = heuristic;
    }

    public int getCounter() {
        return counter;
    }

    public int getExpanded() {
        return expanded;
    }

    public <T extends Comparable<T>> List<Field<T>> solve(Field<T> startPoint) {
        Queue<Field<T>> toVisit = new LinkedList<>();
        HashMap<Field<T>, Field<T>> predecessor = new HashMap<>();

        toVisit.add(startPoint);
        predecessor.put(startPoint, null);
        counter = 0;
        expanded = 1;

        while (toVisit.size() > 0) {
            Field<T> candidate = toVisit.remove();
            counter++;
            if (candidate.isWon()) {
                LinkedList<Field<T>> solution = new LinkedList<>();
                Field<T> backtrace = candidate;
                while (backtrace != null) {
                    solution.addFirst(backtrace);
                    backtrace = predecessor.get(backtrace);
                }
                return solution;
            }
            List<Field<T>> fields = candidate.doAllValidMoves();
            if (heuristic != null) {
                fields = heuristic.sortAfterHeuristic(fields);
            }
            for (Field<T> fp : fields) {
                if (!predecessor.containsKey(fp)) {
                    predecessor.put(fp, candidate);
                    toVisit.add(fp);
                    expanded++;
                }
            }
        }
        return null;
    }

}
