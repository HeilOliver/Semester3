package at.fhv.ohe.function;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.fail;

/**
 * Created by OliverHeil on 06.06.2017.
 */
public class JSONControllerTest {
    @Test
    public void loadSaveFunctions() throws Exception {
        String[] testFunctions = {
                "x^2+(6*2.000000)",
                "x",
                "X+2.12",
                "2^2^2",
                "x^8*2",
                "(((2+3+x))*X)"
        };

        ArrayList<Function> functions = new ArrayList<>();
        for (String s : testFunctions) {
            functions.add(new Function(s));
        }
        Function[] arrayOriginal = functions.toArray(new Function[functions.size()]);
        Function[] arrayAfter;

        JSONController.saveFunctions(arrayOriginal, new File("test.txt"));
        arrayAfter = JSONController.loadFunctions(new File("test.txt"));

        for (int i = 0; i < arrayOriginal.length; i++) {
            Function functionS = arrayOriginal[i];
            boolean isFound = false;

            for (Function function : arrayAfter) {
                if (functionS.getFunctionString().equals(function.getFunctionString()) &&
                        functionS.isVisible() == function.isVisible()) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                fail("@atLoad - " + i);
            }
        }
    }

}