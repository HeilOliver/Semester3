package at.fhv.ohe.function;

import at.fhv.ohe.function.FunctionComponent.FunctionComponentType;
import at.fhv.ohe.function.exceptions.IllegalCalcOperationException;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;
import org.junit.Test;

import static at.fhv.ohe.function.FunctionComponent.FunctionComponentType.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by OliverHeil on 04.06.2017.
 */
public class FunctionTest {
    private static final double EPS = 0.0001;

    @Test
    public void functionExceptionTest() {
        // Building Exception
        String[] tests = {
                "X^2%(6*2.000000)", // Modulo ->+-*/^
                "Y",                // y -> x
                "X+2,12",           // , -> .
                "2^2)2",            // ) -> ^
                "",                 //   -> not Empty
                "--3"               // - -> one - to much
        };

        for (String test : tests) {
            try {
                new Function(test).getFunctionValueAt(0);
                fail("failConEx@ " + test);
            } catch (IllegalFunctionFormatException | IllegalCalcOperationException ignored) {
            }
        }

        // Operational Exception
        String[] tests2 = {
                "X^*+(6*2.000000)", // multiple Operands
                "/",                // only one
        };

        for (String test : tests2) {
            try {
                new Function(test).getFunctionValueAt(0);

                fail("failConEx2@ " + test);
            } catch (IllegalCalcOperationException | IllegalFunctionFormatException ignored) {
            }
        }
    }

    @Test
    public void functionTest() throws Exception {
        String[] testFunctions = {
                "x^2+(6*2.000000)",
                "x",
                "X+2.12",
                "2^2^2",
                "-x+2--3"
        };

        FunctionComponentType[][] testItems = {
                {VARIABLE,
                        EXPONENT,
                        NUMBER,
                        ADDITION,
                        OPENBRACES,
                        NUMBER,
                        MULTIPLICATION,
                        NUMBER,
                        CLOSEBRACES},
                {VARIABLE},
                {VARIABLE,
                        ADDITION,
                        NUMBER},
                {NUMBER,
                        EXPONENT,
                        NUMBER,
                        EXPONENT,
                        NUMBER},
                {NEGVARIABLE,
                        ADDITION,
                        NUMBER,
                        SUBTRACTION,
                        NUMBER}
        };

        double[] testFunctionsResultsAt0 = {
                12,
                0,
                2.12,
                16,
                5
        };
        double[] testFunctionsResultsAt_10 = {
                112,
                -10,
                -7.88,
                16,
                15
        };

        double[] testFunctionsResultsAt10 = {
                112,
                10,
                12.12,
                16,
                -5
        };

        for (int i = 0; i < testFunctions.length; i++) {
            try {
                Function function = new Function(testFunctions[i]);
                FunctionComponent[] components = function._calculator._functionComponents;
                for (int i1 = 0; i1 < components.length; i1++) {
                    assertTrue("failITE@ " + testFunctions[i], testItems[i][i1] == components[i1].getType());
                }

                assertTrue("failVAL0@ " + testFunctions[i],
                        Math.abs(function.getFunctionValueAt(0) - testFunctionsResultsAt0[i]) < EPS);
                assertTrue("failVAL-10@ " + testFunctions[i],
                        Math.abs(function.getFunctionValueAt(-10) - testFunctionsResultsAt_10[i]) < EPS);
                assertTrue("failVAL10@ " + testFunctions[i],
                        Math.abs(function.getFunctionValueAt(10) - testFunctionsResultsAt10[i]) < EPS);
            } catch (IllegalCalcOperationException e) {
                fail("fail@ " + testFunctions[i]);
            } catch (Exception e) {
                e.printStackTrace();
                fail("failOther@ " + testFunctions[i] + "With " + e.getMessage());
            }
        }
    }

}