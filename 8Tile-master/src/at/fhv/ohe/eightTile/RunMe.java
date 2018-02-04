package at.fhv.ohe.eightTile;

import at.fhv.ohe.eightTile.Algorithm.AStare;
import at.fhv.ohe.eightTile.Algorithm.Dijkstra;
import at.fhv.ohe.eightTile.Algorithm.Heuristic.ManhattanDistance;
import at.fhv.ohe.eightTile.Algorithm.Heuristic.MisplacedTiles;
import at.fhv.ohe.eightTile.Algorithm.Heuristic.MyHeuristic;
import at.fhv.ohe.eightTile.Field.Field;
import at.fhv.ohe.eightTile.Field.FieldFactory;
import at.fhv.ohe.eightTile.Field.Node;

import java.util.List;

public class RunMe {

    private static final long SEED = 123;
    private static final int ITERATIONS = 400;

    private static final Node[][] END_FIELD_3x3 = {
            {null, new Node(1), new Node(2)},
            {new Node(3), new Node(4), new Node(5)},
            {new Node(6), new Node(7), new Node(8)}};

    private static final Node[][] END_FIELD_4x4 = {
            {null, new Node(1), new Node(2), new Node(3)},
            {new Node(4), new Node(5), new Node(6), new Node(7)},
            {new Node(8), new Node(9), new Node(10), new Node(11)},
            {new Node(12), new Node(13), new Node(14), new Node(15)}};

    private static final Field<Node> sampleField =
            FieldFactory.createField(END_FIELD_3x3, SEED, ITERATIONS);

    private static final double PRECISION = 0.002;

    public static void main(String[] args) {
        new RunMe().printResults();
    }

    private static double sumOfGeoSeries(double base, int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += Math.pow(base, i);
        }
        return sum;
    }

    private static double calculateBranchingFactor(int expandedNodes, int pathLength) {
        // T+1 = 1+B∗ + (B∗)^2 +⋯+(B∗)^L.
        // numerical resolving - T= expandedNodes, L= PathLength
        double brunch = 0;
        double calcVal = 0.2;
        boolean halfVal = false;

        while (Math.abs((expandedNodes + 1) - sumOfGeoSeries(brunch, pathLength)) > PRECISION) {
            if ((expandedNodes + 1) - sumOfGeoSeries(brunch, pathLength) > 0) {
                if (halfVal) {
                    calcVal = calcVal / 2;
                    halfVal = !halfVal;
                }
                brunch += calcVal;
            } else {
                if (!halfVal) {
                    calcVal = calcVal / 2;
                    halfVal = !halfVal;
                }
                brunch -= calcVal;
            }
        }
        return brunch;
    }

    private void dijkstra_None() {
        System.out.print("none\t\t");
        Dijkstra dijkstra = new Dijkstra();
        List<Field<Node>> solve = dijkstra.solve(sampleField);
        System.out.print(dijkstra.getCounter() + "\t\t");
        System.out.print(solve.size() + "\t\t");
        System.out.print(calculateBranchingFactor(dijkstra.getExpanded(), solve.size()));
        System.out.println();
    }

    private void dijkstra_Manhattan() {
        System.out.print("Manhattan\t");
        Dijkstra dijkstra = new Dijkstra(new ManhattanDistance());
        List<Field<Node>> solve = dijkstra.solve(sampleField);
        System.out.print(dijkstra.getCounter() + "\t\t");
        System.out.print(solve.size() + "\t\t");
        System.out.print(calculateBranchingFactor(dijkstra.getExpanded(), solve.size()));
        System.out.println();
    }

    private void dijkstra_Misplaced() {
        System.out.print("Misplaced\t");
        Dijkstra dijkstra = new Dijkstra(new MisplacedTiles());
        List<Field<Node>> solve = dijkstra.solve(sampleField);
        System.out.print(dijkstra.getCounter() + "\t\t");
        System.out.print(solve.size() + "\t\t");
        System.out.print(calculateBranchingFactor(dijkstra.getExpanded(), solve.size()));
        System.out.println();
    }

    private void dijkstra_MyHeuristic() {
        System.out.print("XOR Both\t");
        Dijkstra dijkstra = new Dijkstra(new MyHeuristic());
        List<Field<Node>> solve = dijkstra.solve(sampleField);
        System.out.print(dijkstra.getCounter() + "\t\t");
        System.out.print(solve.size() + "\t\t");
        System.out.print(calculateBranchingFactor(dijkstra.getExpanded(), solve.size()));
        System.out.println();
    }

    private void aStare_Manhattan() {
        System.out.print("Manhattan\t");
        AStare aStare = new AStare(new ManhattanDistance());
        List<Field<Node>> solve = aStare.solve(sampleField);
        System.out.print(aStare.getCounter() + "\t\t");
        System.out.print(solve.size() + "\t\t");
        System.out.print(calculateBranchingFactor(aStare.getExpanded(), solve.size()));
        System.out.println();
    }

    private void aStare_Misplaced() {
        System.out.print("Misplaced\t");
        AStare aStare = new AStare(new MisplacedTiles());
        List<Field<Node>> solve = aStare.solve(sampleField);
        System.out.print(aStare.getCounter() + "\t\t");
        System.out.print(solve.size() + "\t\t");
        System.out.print(calculateBranchingFactor(aStare.getExpanded(), solve.size()));
        System.out.println();
    }

    private void aStare_MyHeuristic() {
        System.out.print("XOR Both\t");
        AStare aStare = new AStare(new MyHeuristic());
        List<Field<Node>> solve = aStare.solve(sampleField);
        System.out.print(aStare.getCounter() + "\t\t");
        System.out.print(solve.size() + "\t\t");
        System.out.print(calculateBranchingFactor(aStare.getExpanded(), solve.size()));
        System.out.println();
    }

    private void printResults() {
        System.out.println("Settings:");
        System.out.println("Brunching Factor Precision - " + PRECISION);
        System.out.println("Seed - " + SEED);
        System.out.println("Iterations - " + ITERATIONS);
        System.out.println("Field that is been used:");
        System.out.println(sampleField);

        System.out.println("--------------------------------");
        System.out.println("Dijkstra");
        System.out.println("heuristic\tcount\tmoves\tfactor");
        dijkstra_None();
        dijkstra_Misplaced();
        dijkstra_Manhattan();
        dijkstra_MyHeuristic();

        System.out.println("--------------------------------");
        System.out.println("A*");
        System.out.println("heuristic\tcount\tmoves\tfactor");
        aStare_Misplaced();
        aStare_Manhattan();
        aStare_MyHeuristic();
    }
}
