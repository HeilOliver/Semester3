package at.fhv.ohe.graph.interpreter;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphConstructionInterpreter {
    private final static Pattern NUMBER_PATTERN = Pattern.compile("([0-9]+)");
    private final static Pattern EDGE_PATTERN = Pattern.compile("([\\[<](([0-9])+,)*([0-9])+[]>])");

    private GraphConstructionInterpreter() {
    }

    public static Set<Integer> parseVerticesString(String vertexString) {
        Set<Integer> set = new HashSet<>();
        Matcher matcher = NUMBER_PATTERN.matcher(vertexString);
        while (matcher.find()) {
            set.add(Integer.parseInt(matcher.group(1)));
        }
        return set;
    }

    public static Set<EdgeTuple> parseEdgeString(String edgeString) {
        Set<EdgeTuple> set = new HashSet<>();
        Matcher matcher = EDGE_PATTERN.matcher(edgeString);
        while (matcher.find()) {
            int[] numbers = new int[3];
            String matchPattern = matcher.group(1);
            Matcher numberMatcher = NUMBER_PATTERN.matcher(matchPattern);
            for (int i = 0; numberMatcher.find() && i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(numberMatcher.group(1));
            }
            set.add(new EdgeTuple(numbers));
            if (matchPattern.startsWith("<")) { // Add bi directional
                set.add(new EdgeTuple(numbers, true));
            }
        }
        return set;
    }

    public static class EdgeTuple {
        public Integer from;
        public Integer to;
        public Integer weight;

        private EdgeTuple(int[] data) {
            this(data, false);
        }

        private EdgeTuple(int[] data, boolean reverse) {
            if (reverse) {
                from = data[1];
                to = data[0];
            } else {
                from = data[0];
                to = data[1];
            }
            weight = data[2];
        }
    }
}
