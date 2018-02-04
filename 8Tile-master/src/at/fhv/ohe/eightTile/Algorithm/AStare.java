package at.fhv.ohe.eightTile.Algorithm;

import at.fhv.ohe.eightTile.Algorithm.Heuristic.IHeuristic;
import at.fhv.ohe.eightTile.Field.Field;

import java.util.*;

public class AStare implements IAlgorithm {
    private IHeuristic heuristic;
    private int counter;
    private int expanded;

    public AStare(IHeuristic heuristic) {
        this.heuristic = heuristic;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public <T extends Comparable<T>> List<Field<T>> solve(Field<T> startPoint) {
        HashMap<Field<T>, Field<T>> predecessor = new HashMap<>();
        HashMap<Field<T>, Integer> depth = new HashMap<>();
        HashMap<Field<T>, Integer> score = new HashMap<>();
        PriorityQueue<Field<T>> toVisit = new PriorityQueue<>(10000,
                Comparator.comparingInt(score::get));

        predecessor.put(startPoint, null);
        depth.put(startPoint, 0);
        score.put(startPoint, heuristic.getValueFrom(startPoint));
        toVisit.add(startPoint);
        counter = 0;
        expanded = 0;
        while (toVisit.size() > 0) {
            Field<T> candidate = toVisit.remove();
            counter++;

            if (candidate.isWon()) {
                LinkedList<Field<T>> solution = new LinkedList<Field<T>>();
                Field<T> backtrace = candidate;
                while (backtrace != null) {
                    solution.addFirst(backtrace);
                    backtrace = predecessor.get(backtrace);
                }
                return solution;
            }
            for (Field<T> fp : candidate.doAllValidMoves()) {
                if (!predecessor.containsKey(fp)) {
                    predecessor.put(fp, candidate);
                    depth.put(fp, depth.get(candidate) + 1);
                    int estimate = heuristic.getValueFrom(fp);
                    score.put(fp, depth.get(candidate) + 1 + estimate);
                    toVisit.add(fp);
                    expanded++;
                }
            }
        }
        return null;
    }

    public int getExpanded() {
        return expanded;
    }
}
