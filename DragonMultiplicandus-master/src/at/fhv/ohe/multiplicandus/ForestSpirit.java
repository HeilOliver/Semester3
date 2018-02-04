package at.fhv.ohe.multiplicandus;

public class ForestSpirit {
    private JewelOperator operator;
    private int factor;

    public ForestSpirit(JewelOperator operator, int factor) {
        this.operator = operator;
        this.factor = factor;
    }

    public int doMagicThing(int jewels) {
        int calc = operator.calc(jewels, factor);
        return calc < 0 ? -1 : calc;
    }

    @Override
    public String toString() {
        return "ForestSpirit{" +
                "operator=" + operator +
                ", by=" + factor +
                '}';
    }

    public enum JewelOperator {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> b == 0 ? -1 : a / b),
        MULTIPLICATION((a, b) -> a * b);

        private final IJewelOperation operator;

        JewelOperator(IJewelOperation operator) {
            this.operator = operator;
        }

        /**
         * Calculates the new Jewel amount based on the old value an the given factor.
         * Returns an negative integer when an error occurs (Divide throw zero -> -1).
         *
         * @param value - The old Value
         * @param with  - The Factor for the modification
         * @return - A Correct Result when return >= 0 and an error when Result < 0
         */
        public int calc(int value, int with) {
            return operator.doMath(value, with);
        }

        private interface IJewelOperation {
            int doMath(int value, int with);
        }
    }
}
